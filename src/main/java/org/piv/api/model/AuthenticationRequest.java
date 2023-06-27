package org.piv.api.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @NotNull(message = "Login is not be null")
    private String login;
    @NotNull(message = "Password is not be null")
    private String password;
}
