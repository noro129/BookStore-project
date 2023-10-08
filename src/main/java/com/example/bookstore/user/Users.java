package com.example.bookstore.user;

import jakarta.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String addressCity;
    private String addressCountry;
    private String addressZipCode;
    private String phoneNumber;
    private String userRole;

    public Users() {
    }

    public Users(long id,
                 String username,
                 String password,
                 String email,
                 String firstname,
                 String lastname,
                 String addressCity,
                 String addressCountry,
                 String addressZipCode,
                 String phoneNumber,
                 String userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.addressZipCode = addressZipCode;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public Users(String username,
                 String password,
                 String email,
                 String firstname,
                 String lastname,
                 String addressCity,
                 String addressCountry,
                 String addressZipCode,
                 String phoneNumber,
                 String userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.addressZipCode = addressZipCode;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                ", addressZipCode='" + addressZipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}