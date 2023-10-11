package com.example.bookstore.auth;

import com.example.bookstore.user.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String addressCity;
    private String addressCountry;
    private String addressZipCode;
    private String phoneNumber;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, String firstname, String lastname, String addressCity, String addressCountry, String addressZipCode, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.addressZipCode = addressZipCode;
        this.phoneNumber = phoneNumber;
    }
}
