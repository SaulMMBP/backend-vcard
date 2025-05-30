package io.github.saulmmbp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saulmmbp.entities.VCard;

public interface IVCardRepository extends JpaRepository<VCard, Long> {

	Optional<VCard> findByIdAndUserId(Long vcardId, String userId);
	
	Page<VCard> findByUserId(String userId, Pageable pageable);
	
	Long countByUserId(String userId);
	
}
