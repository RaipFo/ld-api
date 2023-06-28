package org.piv.api.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.piv.api.model.AuthenticationRequest;
import org.piv.api.model.AuthenticationResponse;
import org.piv.api.model.RegistrationRequest;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody RegistrationRequest request) {
        String response = authenticationService.register(request);
        if (nonNull(response)) {
            return ResponseEntity.ok(new ResponseMessage(response));
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("Login is taken"));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
