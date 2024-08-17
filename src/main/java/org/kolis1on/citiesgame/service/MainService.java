package org.kolis1on.citiesgame.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.kolis1on.citiesgame.entity.City;
import org.kolis1on.citiesgame.entity.Player;
import org.kolis1on.citiesgame.exception.NotExpectedWordException;
import org.kolis1on.citiesgame.exception.WordNotFoundException;
import org.kolis1on.citiesgame.repository.CityRepository;
import org.kolis1on.citiesgame.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class MainService {

    private final TxtReader txtReader;
    private final CityRepository cityRepository;
    private final PlayerRepository playerRepository;

    private final static String COOKIES_KEY = "player-id";
    public String begin(HttpServletResponse response){

        Player player = new Player();
        playerRepository.save(player);

        Cookie cookie = new Cookie(COOKIES_KEY, String.valueOf(player.getId()));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);

        cookie.setAttribute("SameSite", "None");
        cookie.setSecure(true);

        response.addCookie(cookie);

        String randomCity = txtReader.getRandomCity();
        saveCity(player, randomCity);

        return randomCity;
    }

    public String next(HttpServletRequest request, String playerCity, String currentCity){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new RuntimeException("No cookies found in the request");
        }

        long playerId = Long.parseLong(Arrays.stream(cookies)
                .filter(cookie -> "player-id".equals(cookie.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player ID cookie not found")).getValue());

        Player player = playerRepository.findById(playerId).get();


        char lastLetter = playerCity.toLowerCase().charAt(playerCity.length() - 1);

        if(lastLetter == 'и' || lastLetter == 'ь'){
            lastLetter = playerCity.toLowerCase().charAt(playerCity.length() - 2);
        }
        char firstLetter = playerCity.toLowerCase().charAt(0);

        if(currentCity.charAt(currentCity.length() - 1) != firstLetter){
            throw new NotExpectedWordException("Слово починається з не правильної літери");
        }



        List<String> playerCities = cityRepository.findAllByPlayer(player)
                .stream()
                .map(City::getCity)
                .toList();
        String resultCity = txtReader.getCitiesByLetter(lastLetter)
                .stream()
                .filter(word -> !playerCities.contains(word)).
                findAny().
                orElseThrow(() -> {
                    end(request);
                    return new WordNotFoundException("Система не знайшла слово, ви виграли!");
                });
        saveCity(player,resultCity);
        return resultCity;

    }

    public void end(HttpServletRequest request){
        long playerId = getPlayerIdByCookies(request);
        Player player = playerRepository.findById(playerId).get();
        cityRepository.deleteByPlayer(player);
        playerRepository.delete(player);
    }

    private Long getPlayerIdByCookies(HttpServletRequest request){
        return Long.parseLong(Arrays.stream(request.getCookies())
                .filter(cookie -> COOKIES_KEY.equals(cookie.getName()))
                .findFirst().get().getValue());
    }
    private void saveCity(Player player, String word){
        City city = City.builder()
                .city(word)
                .player(player)
                .build();

        cityRepository.save(city);
    }
}
