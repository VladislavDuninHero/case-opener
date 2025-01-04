package com.case_opener_game.case_opener.repository;

import com.case_opener_game.case_opener.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u WHERE u.login = :login")
    Optional<User> getUserByLogin(String login);
}
