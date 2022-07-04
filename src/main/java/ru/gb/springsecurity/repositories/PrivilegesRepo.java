package ru.gb.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springsecurity.entities.Privileges;

public interface PrivilegesRepo extends JpaRepository<Privileges, Long> {
}
