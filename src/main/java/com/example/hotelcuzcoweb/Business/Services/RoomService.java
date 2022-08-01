package com.example.hotelcuzcoweb.Business.Services;

import com.example.hotelcuzcoweb.Business.Entities.Commands.CreateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Commands.UpdateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Room;
import com.example.hotelcuzcoweb.Business.Exceptions.InvalidRoomIdException;
import com.example.hotelcuzcoweb.Business.Exceptions.NotFoundException;
import com.example.hotelcuzcoweb.DataProviders.Repositories.RoomJdbcRepository;
import com.example.hotelcuzcoweb.DataProviders.Repositories.RoomJpaRepository;
import com.example.hotelcuzcoweb.DataProviders.WebServices.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {

    private final RoomJdbcRepository roomJdbcRepository;
    private final RoomJpaRepository roomJpaRepository;
    private final Mailer mailer;

    @Autowired
    public RoomService(
            RoomJdbcRepository roomJdbcRepository,
            RoomJpaRepository roomJpaRepository,
            Mailer mailer
    ) {
        this.roomJdbcRepository = roomJdbcRepository;
        this.roomJpaRepository = roomJpaRepository;
        this.mailer = mailer;
    }

    public List<Room> findAll() {
        return (List<Room>) roomJpaRepository.findAll();
    }

    public Room findById(int id) throws NotFoundException {
        return roomJpaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Room create(CreateRoom createRoom) throws InvalidRoomIdException {
        Room room = Room.createRoom(createRoom);

        roomJpaRepository.save(room);

        mailer.sendCreateRoomMail(room);

        return room;
    }

    @Transactional
    public Room update(int id, UpdateRoom updateRoom) throws NotFoundException, InvalidRoomIdException {
        Room room = roomJpaRepository.findById(id).orElseThrow(NotFoundException::new);

        Room updatedRoom = room.updateRoom(updateRoom);

        return roomJpaRepository.save(updatedRoom);
    }
}
