package org.piv.api.service;

import org.piv.api.dao.*;
import org.piv.api.model.EventRequest;
import org.piv.api.model.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final RepositoryService repositoryService;
    private final JwtService jwtService;

    public EventService(RepositoryService repositoryService, JwtService jwtService) {
        this.repositoryService = repositoryService;
        this.jwtService = jwtService;
    }

    private User getUserFromBd(String authHeader){
        String jwt = jwtService.getToken(authHeader);
        String userLogin = jwtService.extractLogin(jwt);
        return repositoryService.getUserRepository().findByLogin(userLogin).get();
    }

    public void createEvent(String authHeader, EventRequest request) {
        User user = getUserFromBd(authHeader);
        EventAdmin eventAdmin = repositoryService.getEventAdminRepository().findById(user.getId()).get();
        Event event = Event.builder()
                .eventAdmin(eventAdmin)
                .info(request.getInfo())
                .cost(request.getCost())
                .build();
        repositoryService.getEventRepository().save(event);

        eventAdmin.getEvents().add(event);
        repositoryService.getEventAdminRepository().save(eventAdmin);
    }

    public void registerOnEvent(String authHeader, Long eventId) {
        User user = getUserFromBd(authHeader);
        Participant participant = repositoryService.getParticipantsRepository().findById(user.getId()).get();
        Event event = repositoryService.getEventRepository().findById(eventId).get();
        event.getParticipants().add(participant);
        repositoryService.getEventRepository().save(event);

        participant.getEvents().add(event);
        repositoryService.getParticipantsRepository().save(participant);
    }
}
