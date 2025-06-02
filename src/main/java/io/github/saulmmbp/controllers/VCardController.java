package io.github.saulmmbp.controllers;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.github.saulmmbp.dtos.*;
import io.github.saulmmbp.representationmodels.VCardModel;
import io.github.saulmmbp.services.IVCardService;
import io.github.saulmmbp.utils.assemblers.VCardModelAssembler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("{userId}/vcards")
@AllArgsConstructor
@PreAuthorize("#userId == authentication.principal.subject")
@Slf4j
public class VCardController {

    private IVCardService vcardService;
    private VCardModelAssembler vcardModelAssembler;
    private PagedResourcesAssembler<VCardResponseDto> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<VCardResponseDto> createVCardForUser(@PathVariable String userId,
            @Valid @RequestBody VCardRequestDto vcardRequest) {
        log.info("INICIA CONTROLADOR POST {}/vcards/", userId);
        VCardResponseDto vcard = vcardService.createVCardForUser(userId, vcardRequest);
        log.info("TERMINA CONTROLADOR POST {}/vcards/", userId);
        return new ResponseEntity<>(vcard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedModel<VCardModel>> getVCardsByUserId(@PathVariable String userId,
            @PageableDefault Pageable pageable) {
        log.info("INICIA CONTROLADOR GET {}/vcards/", userId);
        Page<VCardResponseDto> vcards = vcardService.getVCardsByUserId(userId, pageable);
        PagedModel<VCardModel> vcardsPagedModel = pagedResourcesAssembler.toModel(vcards, vcardModelAssembler);
        log.info("TERMINA CONTROLADOR GET {}/vcards/", userId);
        return ResponseEntity.ok(vcardsPagedModel);
    }

    @GetMapping("{vcardId}")
    public ResponseEntity<VCardResponseDto> getVCardByIdAndUserId(@PathVariable String userId,
            @PathVariable Long vcardId) {
        log.info("INICIA CONTROLADOR GET {}/vcards/{}", userId, vcardId);
        VCardResponseDto vcard = vcardService.getVCardByIdAndUserId(vcardId, userId);
        log.info("TERMINA CONTROLADOR GET {}/vcards/{}", userId, vcardId);
        return ResponseEntity.ok(vcard);
    }

    @PutMapping("{vcardId}")
    public ResponseEntity<VCardResponseDto> updateVCardForUser(@PathVariable String userId, @PathVariable Long vcardId,
            @Valid @RequestBody VCardRequestDto vcardRequest) {
        log.info("INICIA CONTROLADOR PUT {}/vcards/{}", userId, vcardId);
        VCardResponseDto vcard = vcardService.updateVCardForUser(vcardId, userId, vcardRequest);
        log.info("TERMINA CONTROLADOR PUT {}/vcards/{}", userId, vcardId);
        return ResponseEntity.ok(vcard);
    }

    @DeleteMapping("{vcardId}")
    public ResponseEntity<?> deleteVCardByIdAndUserId(@PathVariable String userId, @PathVariable Long vcardId) {
        log.info("INICIA CONTROLADOR DELETE {}/vcards/{}", userId, vcardId);
        vcardService.deleteVCardForUser(vcardId, userId);
        log.info("TERMINA CONTROLADOR DELETE {}/vcards/{}", userId, vcardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "{vcardId}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> downloadQRByIdAndUserId(@PathVariable String userId, @PathVariable Long vcardId) {
        log.info("INICIA CONTROLADOR GET {}/vcards/{}/qr", userId, vcardId);
        Resource resource = vcardService.downloadQRImage(vcardId, userId);
        log.info("TERMINA CONTROLADOR GET {}/vcards/{}/qr", userId, vcardId);
        return ResponseEntity.ok(resource);
    }

}
