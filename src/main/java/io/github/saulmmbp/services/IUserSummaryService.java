package io.github.saulmmbp.services;

import io.github.saulmmbp.dtos.SummaryResponseDto;

public interface IUserSummaryService {

	SummaryResponseDto getSummaryByUserId(String userId);
	
}
