package io.github.saulmmbp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saulmmbp.dtos.GeneralInformationDto;
import io.github.saulmmbp.services.IGeneralInformationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("general-information")
@AllArgsConstructor
public class GeneralInformationController {

	private IGeneralInformationService generalInformationService;
	
	@GetMapping("{userId}")
	@PreAuthorize("#userId == authentication.principal.subject")
	public ResponseEntity<GeneralInformationDto> getGeneralInformationByUserId(@PathVariable String userId) {
		GeneralInformationDto generalInformation = generalInformationService.getGeneralInformationByUserId(userId);
		return ResponseEntity.ok(generalInformation);
	}
	
}
