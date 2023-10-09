package com.example.bookstore.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


    @Query("SELECT u FROM Users u WHERE u.username=?1")
    Optional<Users> findUserByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.email=?1")
    Optional<Users> findUserByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.username LIKE %?1%")
    List<Users> searchUsersByUsername(String username);
}
