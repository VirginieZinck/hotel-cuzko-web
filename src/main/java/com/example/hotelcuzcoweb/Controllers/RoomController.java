package com.example.hotelcuzcoweb.Controllers;

import com.example.hotelcuzcoweb.Business.Entities.Commands.CreateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Commands.UpdateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Room;
import com.example.hotelcuzcoweb.Business.Exceptions.InvalidRoomIdException;
import com.example.hotelcuzcoweb.Business.Exceptions.NotFoundException;
import com.example.hotelcuzcoweb.Business.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RoomApi>> findAll() {
        List<Room> rooms = service.findAll();

        return ResponseEntity.ok().body(
                rooms.stream()
                        .map(RoomApi::new)
                        .toList()
        );
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RoomApi> findById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok().body(new RoomApi(
                    service.findById(id)
            ));

        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<RoomApi> create(@Valid @RequestBody CreateRoom createRoom) {
        try {
            return ResponseEntity.ok().body(new RoomApi(
                    service.create(createRoom)
            ));

        } catch (InvalidRoomIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RoomApi> update(@Valid @RequestBody UpdateRoom updateRoom, @PathVariable int id) {
        try {
            return ResponseEntity.ok().body(
                    new RoomApi(
                        service.update(id, updateRoom)
                    )
            );

        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (InvalidRoomIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

