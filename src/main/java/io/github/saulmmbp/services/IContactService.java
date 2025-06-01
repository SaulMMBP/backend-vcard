package io.github.saulmmbp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.saulmmbp.dtos.ContactRequestDto;
import io.github.saulmmbp.dtos.ContactResponseDto;

public interface IContactService {

	Page<ContactResponseDto> getContactsByUserId(String userId, Pageable pageable);
	
	ContactResponseDto getContactByIdAndUserId(Long contactId, String userId);
	
	ContactResponseDto createContactForUser(String userId, ContactRequestDto contactRequest);
	
	ContactResponseDto updateContactForUser(Long contactId, String userId, ContactRequestDto contactRequest);
	
	void deleteContactForUser(Long contactId, String userId);
	
}
