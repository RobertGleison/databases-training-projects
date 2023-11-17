package com.seed.databaseseed.repositories;

import com.seed.databaseseed.entities.Shark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharkRepository extends JpaRepository<Shark, Integer> {
}
