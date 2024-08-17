package org.kolis1on.citiesgame.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kolis1on.citiesgame.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class MainController {

    public final MainService service;

    @GetMapping("/begin")
    @ResponseStatus(HttpStatus.OK)
    public String begin(HttpServletResponse response){
        return service.begin(response);
    }

    @GetMapping("/next")
    @ResponseStatus(HttpStatus.OK)
    public String next(@RequestParam("word") String playerCity,
                       @RequestParam("current-city") String currentCity,
                       HttpServletRequest request,
                       HttpServletResponse response){
        return service.next(request, response, playerCity, currentCity);
    }

    @PostMapping("/end")
    @ResponseStatus(HttpStatus.OK)
    public String endGame(HttpServletRequest request, HttpServletResponse response){
        service.end(request, response);
        return "Спасибі за гру!";
    }

}
