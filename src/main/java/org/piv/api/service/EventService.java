package org.piv.api.service;

import lombok.RequiredArgsConstructor;
import org.piv.api.entity.*;
import org.piv.api.model.EventAdminDTO;
import org.piv.api.model.EventDTO;
import org.piv.api.model.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final RepositoryService repositoryService;
    private final JwtService jwtService;

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
    }

    public EventDTO getEvent(Long eventId) {
        Event event = repositoryService.getEventRepository().findById(eventId).orElseThrow();
        return new EventDTO(
                event.getId(),
                event.getCost(),
                event.getInfo(),
                new EventAdminDTO(
                        event.getEventAdmin().getId(),
                        event.getEventAdmin().getOrgName()));
    }

    public List<EventDTO> getAllEvent() {
        return repositoryService.getEventRepository().findAll()
                .stream()
                .map(event -> new EventDTO(
                        event.getId(),
                        event.getCost(),
                        event.getInfo(),
                        new EventAdminDTO(
                                event.getEventAdmin().getId(),
                                event.getEventAdmin().getOrgName())))
                .collect(Collectors.toList());
    }
}
