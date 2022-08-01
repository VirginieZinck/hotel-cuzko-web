package com.example.hotelcuzcoweb.DataProviders.Repositories;

import com.example.hotelcuzcoweb.Business.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Integer> {

/*    @Query( value = "SELECT * FROM room",
            nativeQuery = true)
    List<Room> findAll();

 */
}
