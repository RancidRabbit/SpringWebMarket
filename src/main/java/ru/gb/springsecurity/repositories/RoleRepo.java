package ru.gb.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springsecurity.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
