package io.github.saulmmbp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saulmmbp.dtos.ContactRequestDto;
import io.github.saulmmbp.dtos.ContactResponseDto;
import io.github.saulmmbp.representationmodels.ContactModel;
import io.github.saulmmbp.services.IContactService;
import io.github.saulmmbp.utils.assemblers.ContactModelAssembler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("{userId}/contacts")
@AllArgsConstructor
@PreAuthorize("#userId == authentication.principal.subject")
@Slf4j
public class ContactController {

    private IContactService contactService;
    private ContactModelAssembler contactModelAssembler;
    private PagedResourcesAssembler<ContactResponseDto> contactPagedAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<ContactModel>> getContactsByUserId(@PathVariable String userId,
            @PageableDefault Pageable pageable) {
        log.info("INICIA CONTROLADOR GET {}/contacts/", userId);
        Page<ContactResponseDto> contacts = contactService.getContactsByUserId(userId, pageable);
        PagedModel<ContactModel> pagedModel = contactPagedAssembler.toModel(contacts, contactModelAssembler);
        log.info("TERMINA CONTROLADOR GET {}/contacts/", userId);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("{contactId}")
    public ResponseEntity<ContactResponseDto> getContactByIdAndUserId(@PathVariable Long contactId,
            @PathVariable String userId) {
        log.info("INICIA CONTROLADOR GET {}/contacts/{}", userId, contactId);
        ContactResponseDto contact = contactService.getContactByIdAndUserId(contactId, userId);
        log.info("TERMINA CONTROLADOR GET {}/contacts/{}", userId, contactId);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContactForUser(@PathVariable String userId,
            @Valid @RequestBody ContactRequestDto contactRequest) {
        log.info("INICIA CONTROLADOR POST {}/contacts/", userId);
        ContactResponseDto contact = contactService.createContactForUser(userId, contactRequest);
        log.info("TERMINA CONTROLADOR POST {}/contacts/", userId);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("{contactId}")
    public ResponseEntity<ContactResponseDto> updateContactForUser(@PathVariable String userId,
            @PathVariable Long contactId, @Valid @RequestBody ContactRequestDto contactRequest) {
        log.info("INICIA CONTROLADOR PUT {}/contacts/{}", userId, contactId);
        ContactResponseDto contact = contactService.updateContactForUser(contactId, userId, contactRequest);
        log.info("TERMINA CONTROLADOR PUT {}/contacts/{}", userId, contactId);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("{contactId}")
    public ResponseEntity<?> deleteContactForUser(@PathVariable String userId, @PathVariable Long contactId) {
        log.info("INICIA CONTROLADOR DELETE {}/contacts/{}", userId, contactId);
        contactService.deleteContactForUser(contactId, userId);
        log.info("TERMINA CONTROLADOR DELETE {}/contacts/{}", userId, contactId);
        return ResponseEntity.noContent().build();
    }

}
