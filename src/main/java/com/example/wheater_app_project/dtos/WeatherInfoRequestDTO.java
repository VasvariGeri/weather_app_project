package com.example.wheater_app_project.dtos;

public class WeatherInfoRequestDTO {

    private String cityName;
    private String country;

    public WeatherInfoRequestDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
