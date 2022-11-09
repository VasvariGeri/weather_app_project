package com.example.wheater_app_project.services;


import com.example.wheater_app_project.dtos.CityToCoordinatesDTO;
import com.example.wheater_app_project.dtos.WeatherInfoDTO;
import com.example.wheater_app_project.dtos.WeatherInfoRequestDTO;
import com.example.wheater_app_project.dtos.WeatherInfoResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

    private CityToCoordinatesDTO convertCityToCoordinates(WeatherInfoRequestDTO weatherInfoRequestDTO) throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/geocoding?city=" + weatherInfoRequestDTO.getCityName());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("x-api-key", System.getenv("GEO_LOCATION_API_KEY"));
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        CityToCoordinatesDTO[] cityToCoordinatesDTOList = mapper.readValue(responseStream, CityToCoordinatesDTO[].class);
        return cityToCoordinatesDTOList[0];
    }

    private WeatherInfoDTO getWeatherInfoByCity(WeatherInfoRequestDTO weatherInfoRequestDTO) throws IOException, InterruptedException {
        CityToCoordinatesDTO coordinates = convertCityToCoordinates(weatherInfoRequestDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.ambeedata.com/weather/latest/by-lat-lng?lat=" + coordinates.getLatitude() +
                        "&lng=" + coordinates.getLongitude()))
                .header("x-api-key", System.getenv("WEATHER_INFO_API_KEY"))
                .header("Content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), WeatherInfoDTO.class);
    }

    @Override
    public WeatherInfoResponseDTO createWeatherInfoResponse(WeatherInfoRequestDTO weatherInfoRequestDTO) throws IOException, InterruptedException {
        WeatherInfoDTO weatherInfoDTO = getWeatherInfoByCity(weatherInfoRequestDTO);
        ModelMapper mapper = new ModelMapper();
        WeatherInfoResponseDTO weatherInfoResponseDTO = mapper.map(weatherInfoDTO.getData(), WeatherInfoResponseDTO.class);
        weatherInfoResponseDTO.setCity(weatherInfoRequestDTO.getCityName());
        weatherInfoResponseDTO.setCountryCode(weatherInfoRequestDTO.getCountry());
        return weatherInfoResponseDTO;
    }
}
