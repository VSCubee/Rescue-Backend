package com.rescue.vscube.event;


import com.rescue.vscube.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/events")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("")
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events =  eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        return new ResponseEntity<Event>(eventService.addEvent(event),HttpStatus.OK);
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long event_id){
        return new ResponseEntity<>(eventService.getEventById(event_id),HttpStatus.OK);
    }

    @PutMapping("/{event_id}/agency/{agency_id}")
    public ResponseEntity<Void> addAgency(@PathVariable Long event_id,@PathVariable Long agency_id){
        eventService.addAgency(event_id,agency_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event){
        return ResponseEntity.ok(eventService.updateEvent(event));
    }


}
