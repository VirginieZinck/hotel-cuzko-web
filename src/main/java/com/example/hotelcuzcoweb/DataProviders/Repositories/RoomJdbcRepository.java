package com.example.hotelcuzcoweb.DataProviders.Repositories;

import com.example.hotelcuzcoweb.Business.Entities.Accessory;
import com.example.hotelcuzcoweb.Business.Entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Room> findAllCustom() {
        return jdbcTemplate.query(
                "select * from room",
                (rs, rowNum) ->
                        new Room(
                                rs.getInt("id"),
                                rs.getInt("floor_number"),
                                rs.getString("description"),
                                rs.getInt("capacity"),
                                List.of()
                        )
        );
    }
}
