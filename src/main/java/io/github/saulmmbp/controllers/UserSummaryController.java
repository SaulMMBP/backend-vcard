package io.github.saulmmbp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saulmmbp.dtos.SummaryResponseDto;
import io.github.saulmmbp.services.IUserSummaryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("{userId}/summary")
@AllArgsConstructor
@PreAuthorize("#userId == authentication.principal.subject")
public class UserSummaryController {

	private IUserSummaryService userSummaryService;
	
	@GetMapping
	public ResponseEntity<SummaryResponseDto> getGeneralInformationByUserId(@PathVariable String userId) {
		SummaryResponseDto generalInformation = userSummaryService.getSummaryByUserId(userId);
		return ResponseEntity.ok(generalInformation);
	}
	
}
