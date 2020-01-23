package com.ris.ris.project.repository;

import com.ris.ris.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
