package com.example.hotelcuzcoweb.Business.Entities.Commands;


import com.example.hotelcuzcoweb.Business.Entities.Accessory;

import javax.validation.constraints.NotNull;
import java.util.List;

public record UpdateRoom(
        @NotNull(message = "Floor number cannot be null")
        int floorNumber,

        String description,

        @NotNull(message = "Capacity cannot be null")
        int capacity,

        List<Accessory> accessories
) {
}
