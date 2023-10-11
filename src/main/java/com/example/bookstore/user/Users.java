package com.example.bookstore.user;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Table
public class Users implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role userRole;
    private boolean activated;

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
                 Role userRole,
                 boolean activated) {
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
        this.activated=activated;
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
                 Role userRole,
                 boolean activated) {
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
        this.activated=activated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
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