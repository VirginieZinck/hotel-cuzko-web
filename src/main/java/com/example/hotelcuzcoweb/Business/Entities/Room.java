package com.example.hotelcuzcoweb.Business.Entities;

import com.example.hotelcuzcoweb.Business.Exceptions.InvalidRoomIdException;
import com.example.hotelcuzcoweb.Business.Entities.Commands.CreateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Commands.UpdateRoom;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Builder(toBuilder = true)
@Entity
@ToString
@EqualsAndHashCode
public class Room {

    public static final String DEFAULT_DESCRIPTION = "My beautiful room";

    @Id
    public int id;

    public int floorNumber;

    @ColumnDefault(DEFAULT_DESCRIPTION)
    public String description = DEFAULT_DESCRIPTION;

    public int capacity;

    @OneToMany(mappedBy="room", cascade = CascadeType.ALL)
    public List<Accessory> accessories;

    public Room(int id, int floorNumber, String description, int capacity, List<Accessory> accessories) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.description = description;
        this.capacity = capacity;
        this.accessories = accessories;
    }

    public Room() {
    }

    public static Room createRoom(CreateRoom createRoom) throws InvalidRoomIdException {
        if (doesRoomStartWithFloorNumber(createRoom.id(), createRoom.floorNumber())) {
            throw new InvalidRoomIdException();
        }

        Room room = Room.builder()
                .id(createRoom.id())
                .floorNumber(createRoom.floorNumber())
                .description(createRoom.description())
                .accessories(createRoom.accessories())
                .build();

        room.toBuilder().floorNumber(123).build();

        if (room.description == null) {
            room.description = DEFAULT_DESCRIPTION;
        }

        return room;
    }

    public Room updateRoom(UpdateRoom updateRoom) throws InvalidRoomIdException {
        if (doesRoomStartWithFloorNumber(this.id, updateRoom.floorNumber())) {
            throw new InvalidRoomIdException();
        }

        this.floorNumber = updateRoom.floorNumber();
        this.capacity = updateRoom.capacity();
        this.accessories = updateRoom.accessories();
        this.description = DEFAULT_DESCRIPTION;

        if (updateRoom.description() != null) {
            this.description = updateRoom.description();
        }

        return this;
    }

    private static boolean doesRoomStartWithFloorNumber(int id, int floorNumber) {
        int floorNumberInRoomNumber = id / 100;
        return (floorNumber != floorNumberInRoomNumber);
    }
}
