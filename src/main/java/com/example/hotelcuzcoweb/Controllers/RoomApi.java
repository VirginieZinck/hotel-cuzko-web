package com.example.hotelcuzcoweb.Controllers;

import com.example.hotelcuzcoweb.Business.Entities.Accessory;
import com.example.hotelcuzcoweb.Business.Entities.Room;

import java.util.List;

public record RoomApi(int id,
                      int floorNumber,
                      String description,
                      int capacity,
                      List<Accessory> accessories
) {

    public RoomApi(Room room) {
        this(
                room.id,
                room.floorNumber,
                room.description,
                room.capacity,
                room.accessories
        );
    }
}
