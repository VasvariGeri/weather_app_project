package com.example.wheater_app_project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class City {
    @Id
    private Long id;
    private String cityName;
    @ManyToMany
    private List<User> users;

}
