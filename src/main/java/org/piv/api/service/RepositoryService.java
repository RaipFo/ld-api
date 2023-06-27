package org.piv.api.service;

import lombok.Getter;
import org.piv.api.repository.*;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RepositoryService {
    private final ContractRepository contractRepository;
    private final EventAdminRepository eventAdminRepository;
    private final PrincipalRepository principalRepository;
    private final UserRepository userRepository;
    private final ParticipantsRepository participantsRepository;
    private final EventRepository eventRepository;

    public RepositoryService(ContractRepository contractRepository, EventAdminRepository eventAdminRepository,
                             PrincipalRepository principalRepository, UserRepository userRepository,
                             ParticipantsRepository participantsRepository, EventRepository eventRepository) {
        this.contractRepository = contractRepository;
        this.eventAdminRepository = eventAdminRepository;
        this.principalRepository = principalRepository;
        this.userRepository = userRepository;
        this.participantsRepository = participantsRepository;
        this.eventRepository = eventRepository;
    }
}
