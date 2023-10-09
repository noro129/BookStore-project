package com.example.bookstore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public List<Users> getUserById(@PathVariable("userId") Long userId){
        return usersService.getUserById(userId);
    }

    @GetMapping(path = "/username/{username}")
    public List<Users> getUserByUsername(@PathVariable("username") String username){
        return usersService.getUserByUsername(username);
    }

    @GetMapping(path = "search")
    public List<Users> searchUsers(@RequestParam("username") String username){
        return usersService.searchUsersByUsername(username);
    }

    @PostMapping
    public void addUser(@RequestBody Users user){
        usersService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        usersService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String email){
        usersService.updateUser(userId, username, email);
    }
}
