package io.github.saulmmbp.services.impl;

import org.springframework.stereotype.Service;

import io.github.saulmmbp.dtos.SummaryResponseDto;
import io.github.saulmmbp.repositories.IContactRepository;
import io.github.saulmmbp.repositories.IVCardRepository;
import io.github.saulmmbp.services.IUserSummaryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSummaryService implements IUserSummaryService {

	private IVCardRepository vcardRepository;
	private IContactRepository contactRepository;
	
	@Override
	public SummaryResponseDto getSummaryByUserId(String userId) {
		long vcardsTotal = vcardRepository.countByUserId(userId);
		long contactsTotal = contactRepository.countByUserId(userId);
		return new SummaryResponseDto(contactsTotal, vcardsTotal);
	}
	
}
