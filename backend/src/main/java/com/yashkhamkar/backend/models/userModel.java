package com.yashkhamkar.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class userModel {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;

}
