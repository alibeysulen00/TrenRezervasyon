package com.example.trenrezervasyon.repository;

import com.example.trenrezervasyon.entities.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Long> {

}
