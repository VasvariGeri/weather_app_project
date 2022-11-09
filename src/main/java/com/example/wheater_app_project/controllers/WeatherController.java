package com.example.wheater_app_project.controllers;

import com.example.wheater_app_project.dtos.WeatherInfoRequestDTO;
import com.example.wheater_app_project.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get-weather")
    public ResponseEntity<?> getWeatherInfo(@RequestBody WeatherInfoRequestDTO weatherInfoRequestDTO) throws IOException, InterruptedException {
        return ResponseEntity.ok(weatherService.createWeatherInfoResponse(weatherInfoRequestDTO));
    }
}
