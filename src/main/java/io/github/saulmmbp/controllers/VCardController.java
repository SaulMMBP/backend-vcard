package io.github.saulmmbp.controllers;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.github.saulmmbp.dtos.VCardRequestDto;
import io.github.saulmmbp.dtos.VCardResponseDto;
import io.github.saulmmbp.representationmodels.VCardModel;
import io.github.saulmmbp.services.IVCardService;
import io.github.saulmmbp.utils.assemblers.VCardModelAssembler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("{userId}/vcards")
@AllArgsConstructor
@PreAuthorize("#userId == authentication.principal.subject")
public class VCardController {
	
	private IVCardService vcardService;
	private VCardModelAssembler vcardModelAssembler;
	private PagedResourcesAssembler<VCardResponseDto> pagedResourcesAssembler;

	@PostMapping
	public ResponseEntity<VCardResponseDto> createVCardForUser(@PathVariable String userId, @Valid @RequestBody VCardRequestDto vcardRequest) {
		VCardResponseDto vcard = vcardService.createVCardForUser(userId, vcardRequest);
		return new ResponseEntity<>(vcard, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<PagedModel<VCardModel>> getVCardsByUserId(@PathVariable String userId, @PageableDefault Pageable pageable) {
		Page<VCardResponseDto> vcards = vcardService.getVCardsByUserId(userId, pageable);
		PagedModel<VCardModel> vcardsPagedModel = pagedResourcesAssembler.toModel(vcards, vcardModelAssembler);
		return ResponseEntity.ok(vcardsPagedModel);
	}
	
	@GetMapping("{vcardId}")
	public ResponseEntity<VCardResponseDto> getVCardByIdAndUserId(@PathVariable String userId, @PathVariable Long vcardId) {
		VCardResponseDto vcard = vcardService.getVCardByIdAndUserId(vcardId, userId);
		return ResponseEntity.ok(vcard);
	}
	
	@PutMapping("{vcardId}")
	public ResponseEntity<VCardResponseDto> updateVCardForUser(@PathVariable String userId, @PathVariable Long vcardId, @Valid @RequestBody VCardRequestDto vcardRequest) {
		VCardResponseDto vcard = vcardService.updateVCardForUser(vcardId, userId, vcardRequest);
		return ResponseEntity.ok(vcard);
	}
	
	@DeleteMapping("{vcardId}")
	public ResponseEntity<?> deleteVCardByIdAndUserId(@PathVariable String userId, @PathVariable Long vcardId) {
		vcardService.deleteVCardForUser(vcardId, userId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "{vcardId}/qr", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<Resource> downloadQRByIdAndUserId(@PathVariable String userId, @PathVariable Long vcardId) {
		Resource resource = vcardService.downloadQRImage(vcardId, userId);
		return ResponseEntity.ok(resource);
	}
	
}
