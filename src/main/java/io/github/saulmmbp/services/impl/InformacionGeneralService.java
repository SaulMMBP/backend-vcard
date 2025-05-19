package io.github.saulmmbp.services.impl;

import org.springframework.stereotype.Service;

import io.github.saulmmbp.dtos.GeneralInformationDto;
import io.github.saulmmbp.repositories.IContactRepository;
import io.github.saulmmbp.repositories.IVCardRepository;
import io.github.saulmmbp.services.IGeneralInformationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InformacionGeneralService implements IGeneralInformationService {

	private IVCardRepository vcardRepository;
	private IContactRepository contactRepository;
	
	@Override
	public GeneralInformationDto getGeneralInformationByUserId(String userId) {
		long vcardsTotal = vcardRepository.countByUserId(userId);
		long contactsTotal = contactRepository.countByUserId(userId);
		return new GeneralInformationDto(contactsTotal, vcardsTotal);
	}
	
}
