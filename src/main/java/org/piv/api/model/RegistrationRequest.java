package org.piv.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.piv.api.model.enums.Role;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotNull(message = "Login is not be null")
    private String login;
    @NotNull(message = "Password is not be null")
    private String password;
    @NotNull(message = "Role is not be null")
    private Role role;
    private Long inn;
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private String org_name;
}
