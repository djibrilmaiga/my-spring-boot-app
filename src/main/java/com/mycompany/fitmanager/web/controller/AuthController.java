package com.mycompany.fitmanager.web.controller;

import com.mycompany.fitmanager.web.dto.AuthenticationRequest;
import com.mycompany.fitmanager.web.dto.AuthenticationResponse;
import com.mycompany.fitmanager.web.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthentificationService authentificationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return  ResponseEntity.ok(authentificationService.authenticate(authRequest));
    }
}
