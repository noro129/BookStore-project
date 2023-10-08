package com.example.bookstore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
