package io.github.saulmmbp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.VCard;

public interface IVCardRepository extends JpaRepository<VCard, Long> {

	List<VCard> findByUserId(String userId);
	
	Long countByUserId(String userId);
	
}
