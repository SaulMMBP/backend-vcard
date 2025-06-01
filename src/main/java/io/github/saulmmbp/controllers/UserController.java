package io.github.saulmmbp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saulmmbp.dtos.UserRequestDto;
import io.github.saulmmbp.services.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

	private IUserService userService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_backend-vcard/users/create')")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequest) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Authorities: " + auth.getAuthorities());
		userService.createUser(userRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
