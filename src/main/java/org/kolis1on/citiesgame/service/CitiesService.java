package org.kolis1on.citiesgame.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CitiesService {

    public static final List<String> cities = new ArrayList<>();

    static{
        cities.add("Київ");
        cities.add("Львів");
        cities.add("Харків");
        cities.add("Одеса");
        cities.add("Дніпро");
        cities.add("Запоріжжя");
        cities.add("Черкаси");
        cities.add("Чернівці");
        cities.add("Вінниця");
        cities.add("Полтава");
        cities.add("Луцьк");
        cities.add("Рівне");
        cities.add("Кропивницький");
        cities.add("Житомир");
        cities.add("Тернопіль");
        cities.add("Суми");
        cities.add("Миколаїв");
        cities.add("Хмельницький");
        cities.add("Ужгород");
        cities.add("Івано-Франківськ");
        cities.add("Кременчук");
        cities.add("Кам'янське");
        cities.add("Бердянськ");
        cities.add("Біла Церква");
        cities.add("Павлоград");
        cities.add("Слов'янськ");
        cities.add("Нікополь");
        cities.add("Лисичанськ");
        cities.add("Сєвєродонецьк");
        cities.add("Мелітополь");
        cities.add("Бориспіль");
        cities.add("Бровари");
        cities.add("Луганськ");
        cities.add("Краматорськ");
        cities.add("Алчевськ");
        cities.add("Пирятин");
        cities.add("Каховка");
        cities.add("Херсон");
        cities.add("Южноукраїнськ");
        cities.add("Шостка");
        cities.add("Бердичів");
        cities.add("Миргород");
        cities.add("Яготин");
        cities.add("Свалява");
        cities.add("Дрогобич");
        cities.add("Мукачево");
        cities.add("Синельникове");
        cities.add("Славутич");
        cities.add("Новомосковськ");
        cities.add("Чугуїв");
        cities.add("Ніжин");
        cities.add("Городня");
        cities.add("Сіверськ");
        cities.add("Світловодськ");
        cities.add("Стаханов");
        cities.add("Лубни");
        cities.add("Хуст");
        cities.add("Коломия");
        cities.add("Дубно");
        cities.add("Броди");
        cities.add("Олександрія");
        cities.add("Костянтинівка");
        cities.add("Жовті Води");
        cities.add("Ромни");
        cities.add("Летичів");
        cities.add("Деражня");
        cities.add("Гайсин");
        cities.add("Чортків");
        cities.add("Вишгород");
        cities.add("Ладижин");
        cities.add("Острог");
        cities.add("Сєвєродонецьк");
        cities.add("Добропілля");
        cities.add("Тростянець");
        cities.add("Васильків");
        cities.add("Рівне");
        cities.add("Мирноград");
        cities.add("Чернігів");
        cities.add("Боярка");
        cities.add("Новоград-Волинський");
        cities.add("Яремче");
        cities.add("Костопіль");
        cities.add("Суми");
        cities.add("Бурштин");
        cities.add("Рожище");
        cities.add("Конотоп");
        cities.add("Красноград");
        cities.add("Новий Буг");
        cities.add("Лозова");
        cities.add("Берегове");
        cities.add("Калуш");
        cities.add("Буча");
        cities.add("Чернівці");
        cities.add("Теплодар");
        cities.add("Лозова");
        cities.add("Умань");
        cities.add("Ладижин");
        cities.add("Горлівка");
    }
    public List<String> getCitiesByLetter(char letter) {
        List<String> result = new ArrayList<>();

        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("The provided symbol is not a letter!");
        }

        letter = Character.toUpperCase(letter);

        for (String city : cities) {
            if (!city.isEmpty() && Character.toUpperCase(city.charAt(0)) == letter) {
                result.add(city);
            }
        }

        return result;
    }

    public String getRandomCity() {
        if (cities.isEmpty()) {
            return null; // Return null if the list is empty
        }

        Random random = new Random();
        return cities.get(random.nextInt(cities.size()));
    }
}
