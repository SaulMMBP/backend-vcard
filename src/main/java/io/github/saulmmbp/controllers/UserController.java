package io.github.saulmmbp.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.github.saulmmbp.dtos.UserRequestDto;
import io.github.saulmmbp.services.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequest) {
        log.info("INICIA CONTROLADOR POST users");
        userService.createUser(userRequest);
        log.info("TERMINA CONTROLADOR POST users");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
