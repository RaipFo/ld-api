package org.piv.api.controller;

import org.piv.api.model.EventRequest;
import org.piv.api.model.RegistrationRequest;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.EventService;
import org.piv.api.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final JwtService jwtService;
    private final EventService eventService;

    public EventController(JwtService jwtService, EventService eventService) {
        this.jwtService = jwtService;
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createEvent(@RequestHeader(name = "Authorization") String authHeader,
                                                       @Valid @RequestBody EventRequest request) {
        eventService.createEvent(authHeader, request);
        return ResponseEntity.ok(new ResponseMessage("Event created"));
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<ResponseMessage> registerOnEvent(@PathVariable("id") Long eventId,
                                                           @RequestHeader(name = "Authorization") String authHeader) {
        eventService.registerOnEvent(authHeader, eventId);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }
}
