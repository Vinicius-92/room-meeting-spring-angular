package com.viniciusaugusto.Meeting.Room.controller;

import com.viniciusaugusto.Meeting.Room.exception.ResourceNotFoundException;
import com.viniciusaugusto.Meeting.Room.model.Room;
import com.viniciusaugusto.Meeting.Room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository repository;

    @GetMapping("/rooms")
    public List<Room> findAll() {
        return repository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return repository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room roomToUpdate) {
        Room room = repository.findById(id).orElseThrow(() -> new ResourceAccessException("Room not found: " + id));
        room.setName(roomToUpdate.getName());
        room.setDate(roomToUpdate.getDate());
        room.setStartHour(roomToUpdate.getStartHour());
        room.setEndHour(roomToUpdate.getEndHour());
        final Room updateRoom = repository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long id) {
        Room room = repository.findById(id).orElseThrow(() -> new ResourceAccessException("Room not found: " + id));
        repository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
