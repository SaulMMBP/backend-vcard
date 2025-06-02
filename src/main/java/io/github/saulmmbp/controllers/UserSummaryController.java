package io.github.saulmmbp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.github.saulmmbp.dtos.SummaryResponseDto;
import io.github.saulmmbp.services.IUserSummaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("{userId}/summary")
@AllArgsConstructor
@PreAuthorize("#userId == authentication.principal.subject")
@Slf4j
public class UserSummaryController {

    private IUserSummaryService userSummaryService;

    @GetMapping
    public ResponseEntity<SummaryResponseDto> getGeneralInformationByUserId(@PathVariable String userId) {
        log.info("INICIA CONTROLADOR GET {}/summary/", userId);
        SummaryResponseDto generalInformation = userSummaryService.getSummaryByUserId(userId);
        log.info("TERMINA CONTROLADOR GET {}/summary/", userId);
        return ResponseEntity.ok(generalInformation);
    }

}
