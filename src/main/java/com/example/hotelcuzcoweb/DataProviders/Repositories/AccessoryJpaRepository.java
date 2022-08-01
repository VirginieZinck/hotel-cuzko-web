package com.example.hotelcuzcoweb.DataProviders.Repositories;

import com.example.hotelcuzcoweb.Business.Entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryJpaRepository extends JpaRepository<Accessory, Integer> {
}
