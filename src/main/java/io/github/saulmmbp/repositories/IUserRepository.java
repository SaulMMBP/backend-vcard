package io.github.saulmmbp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.User;

public interface IUserRepository extends JpaRepository<User, String> {

}
