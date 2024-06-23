package com.java.cidade.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.java.cidade.dtos.authentication.AuthenticationRequest;
import com.java.cidade.dtos.authentication.AuthenticationResponse;
import com.java.cidade.dtos.authentication.RegisterRequest;
import com.java.cidade.core.exceptions.BadRequestException;
import com.java.cidade.services.AuthService;
import com.java.cidade.core.utils.JsonMessage;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "API de Autenticação de usuários.")
public class AuthController {

    private final AuthService authService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Registra um novo usuário.", method = "POST")
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Parameter(description = "Dados para registrar novo usuário") @RequestBody RegisterRequest registerRequest
    ) {
        AuthenticationResponse authenticationResponse = this.authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Recurso para efetuar login no sistema.", method = "POST")
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(
            @Parameter(description = "Dados para autenticação") @RequestBody AuthenticationRequest authenticationRequest
    ) {
        AuthenticationResponse authenticationResponse = this.authService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);

    }

}
