package org.piv.api.controller;

import lombok.RequiredArgsConstructor;
import org.piv.api.model.EventRequest;
import org.piv.api.model.RegistrationRequest;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.EventService;
import org.piv.api.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {
    private final JwtService jwtService;
    private final EventService eventService;

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
