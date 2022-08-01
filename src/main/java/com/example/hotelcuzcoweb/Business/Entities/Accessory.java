package com.example.hotelcuzcoweb.Business.Entities;

import com.example.hotelcuzcoweb.Business.Entities.Enums.AccessoryType;

import javax.persistence.*;

@Entity
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Enumerated(EnumType.STRING)
    public AccessoryType type;

    public String description;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
}
