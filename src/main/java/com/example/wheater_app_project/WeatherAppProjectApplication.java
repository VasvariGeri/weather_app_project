package com.example.wheater_app_project;

import com.example.wheater_app_project.models.User;
import com.example.wheater_app_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class WeatherAppProjectApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.ambeedata.com/latest/by-lat-lng?lat=12.9889055&lng=77.574044"))
//                .header("x-api-key", "11ecca9105a6592b6c9e3568d61ad2b356e9d655310e23306e188197bf14769d")
//                .header("Content-type", "application/json")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        SpringApplication.run(WeatherAppProjectApplication.class, args);
    }
}
