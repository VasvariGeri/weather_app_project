package com.example.wheater_app_project.services;

import com.example.wheater_app_project.dtos.WeatherInfoRequestDTO;
import com.example.wheater_app_project.dtos.WeatherInfoResponseDTO;

import java.io.IOException;

public interface WeatherService {

    WeatherInfoResponseDTO createWeatherInfoResponse(WeatherInfoRequestDTO weatherInfoRequestDTO) throws IOException, InterruptedException;
}
