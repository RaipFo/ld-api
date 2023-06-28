package org.piv.api.controller;

import lombok.RequiredArgsConstructor;
import org.piv.api.model.EventDTO;
import org.piv.api.model.EventRequest;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createEvent(@RequestHeader(name = "Authorization") String authHeader,
                                                       @Valid @RequestBody EventRequest request) {
        eventService.createEvent(authHeader, request);
        return ResponseEntity.ok(new ResponseMessage("Event created"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(eventService.getEvent(eventId));
    }
    @GetMapping()
    public ResponseEntity<List<EventDTO>> getEvents() {
        return ResponseEntity.ok(eventService.getAllEvent());
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<ResponseMessage> registerOnEvent(@PathVariable("id") Long eventId,
                                                           @RequestHeader(name = "Authorization") String authHeader) {
        eventService.registerOnEvent(authHeader, eventId);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }
}
