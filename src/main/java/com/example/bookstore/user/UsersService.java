package com.example.bookstore.user;

import org.aspectj.weaver.loadtime.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    UsersService(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    public void addUser(Users user) {
        Optional<Users> userByUsername = usersRepository.findUserByUsername(user.getUsername());
        if(userByUsername.isPresent()){
            throw new IllegalStateException("username already in use");
        }
        Optional<Users> userByEmail = usersRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("email already in use");
        }
        usersRepository.save(user);
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public void deleteUser(Long userId) {
        boolean userExists = usersRepository.existsById(userId);
        if(userExists){
            usersRepository.deleteById(userId);
        } else {
            throw new IllegalStateException("user with id "+userId+" does not exist");
        }
    }

    @Transactional
    public void updateUser(Long userId, String username, String email) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("user with id "+userId+" does not exist."));
        if(username!=null && !username.isEmpty() && !Objects.equals(user.getUsername(),username)){
            Optional<Users> userByUsername = usersRepository.findUserByUsername(username);
            if(userByUsername.isPresent()){
                throw new IllegalStateException("the username "+username+" is already in use.");
            }
            user.setUsername(username);
        }
        if(email!=null && !email.isEmpty() && !Objects.equals(user.getEmail(),email)){
            Optional<Users> userByEmail = usersRepository.findUserByEmail(email);
            if(userByEmail.isPresent()){
                throw new IllegalStateException("the email "+email+" is already in use.");
            }
            user.setEmail(email);
        }
    }

    public List<Users> getUserById(Long userId) {
        Optional<Users> userById = usersRepository.findById(userId);
        if(userById.isPresent()){ return userById.map(Collections::singletonList).orElse(Collections.emptyList()); }
        else { throw new IllegalStateException("user with id "+userId+" does not exist"); }
    }

    public List<Users> getUserByUsername(String username) {
        Optional<Users> userByUsername = usersRepository.findUserByUsername(username);
        if(userByUsername.isPresent()){ return userByUsername.map(Collections::singletonList).orElse(Collections.emptyList()); }
        else { throw new IllegalStateException("user with username "+username+" does not exist"); }
    }

    public List<Users> searchUsersByUsername(String username) {
        if(username.isEmpty()) return Collections.emptyList();
        return usersRepository.searchUsersByUsername(username);
    }
}
