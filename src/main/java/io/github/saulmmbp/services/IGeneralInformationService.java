package io.github.saulmmbp.services;

import io.github.saulmmbp.dtos.GeneralInformationDto;

public interface IGeneralInformationService {

	GeneralInformationDto getGeneralInformationByUserId(String userId);
	
}
