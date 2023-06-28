package org.piv.api.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.piv.api.repository.*;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class RepositoryService {
    private final ContractRepository contractRepository;
    private final EventAdminRepository eventAdminRepository;
    private final PrincipalRepository principalRepository;
    private final UserRepository userRepository;
    private final ParticipantsRepository participantsRepository;
    private final EventRepository eventRepository;
}
