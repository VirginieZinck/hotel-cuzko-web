package com.example.hotelcuzcoweb.Business.Entities.Commands;

import com.example.hotelcuzcoweb.Business.Entities.Accessory;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public record CreateRoom(
        @Positive
        @NotNull(message = "Room id cannot be null")
        int id,

        @Positive
        @NotNull(message = "Floor number cannot be null")
        int floorNumber,

        String description,

        @Positive
        @NotNull(message = "Capacity cannot be null")
        int capacity,

        List<Accessory> accessories
) {
}
