package org.kolis1on.citiesgame.service;

import org.kolis1on.citiesgame.exception.SymbolIsNotLetterException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TxtReader {

    private static final String FILE_PATH = "D:\\CitiesGame\\src\\main\\resources\\cities.txt";
    public List<String> getCitiesByLetter(char letter) {
        List<String> cities = new ArrayList<>();

        if (!Character.isLetter(letter)) {
            throw new SymbolIsNotLetterException("Символ не є буквою!");
        }

        letter = Character.toUpperCase(letter);

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && Character.toUpperCase(line.charAt(0)) == letter) {
                    cities.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public String getRandomCity() {
        List<String> cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    cities.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cities.isEmpty()) {
            return null; // Якщо файл порожній
        }

        Random random = new Random();
        return cities.get(random.nextInt(cities.size()));
    }
}
