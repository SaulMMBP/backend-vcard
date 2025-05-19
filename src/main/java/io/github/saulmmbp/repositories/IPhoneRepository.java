package io.github.saulmmbp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.Phone;

public interface IPhoneRepository extends JpaRepository<Phone, Long> {

}
