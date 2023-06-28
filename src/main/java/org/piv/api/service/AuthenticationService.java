package org.piv.api.service;

import lombok.RequiredArgsConstructor;
import org.piv.api.entity.*;
import org.piv.api.model.*;
import org.piv.api.model.enums.Role;
import org.piv.api.repository.EventAdminRepository;
import org.piv.api.repository.ParticipantsRepository;
import org.piv.api.repository.PrincipalRepository;
import org.piv.api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PrincipalRepository principalRepository;
    private final EventAdminRepository eventAdminRepository;
    private final ParticipantsRepository participantsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegistrationRequest request) {
        String login = request.getLogin();
        if (userRepository.findByLogin(login).isEmpty()) {
            Role role = request.getRole();
            User user = User.builder()
                    .login(login)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userRepository.save(user);
            switch (role) {
                case PRINCIPAL: {
                    Principal principal = Principal.builder()
                            .inn(request.getInn())
                            .user(user)
                            .build();
                    principalRepository.save(principal);
                    break;
                }
                case EVENT_ADMIN:{
                    EventAdmin eventAdmin = EventAdmin.builder()
                            .orgName(request.getOrg_name())
                            .user(user)
                            .build();
                    eventAdminRepository.save(eventAdmin);
                    break;
                }
                case PARTICIPANT: {
                    Participant participant = Participant.builder()
                            .surname(request.getSurname())
                            .name(request.getName())
                            .patronymic(request.getPatronymic())
                            .age(request.getAge())
                            .user(user)
                            .build();
                    participantsRepository.save(participant);
                    break;
                }
                default:
                    return null;
            }
            return "Registration successful";
        }
        else
            return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        String jwt = jwtService.generateToken(userRepository.findByLogin(request.getLogin()).orElseThrow());
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
