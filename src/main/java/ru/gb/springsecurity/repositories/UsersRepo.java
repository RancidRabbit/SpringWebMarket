package ru.gb.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springsecurity.entities.Users;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String name);

}
