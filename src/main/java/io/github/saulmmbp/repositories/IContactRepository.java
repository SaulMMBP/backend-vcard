package io.github.saulmmbp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.Contact;

public interface IContactRepository extends JpaRepository<Contact, Long> {

    Long countByUserId(String userId);

    Page<Contact> findAllByUserId(String userId, Pageable pageable);

    Optional<Contact> findByIdAndUserId(Long contactId, String userId);

}
