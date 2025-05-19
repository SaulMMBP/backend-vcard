package io.github.saulmmbp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.Contact;

public interface IContactRepository extends JpaRepository<Contact, Long>{

	Long countByUserId(String userId);
	
}
