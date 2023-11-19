package com.seed.databaseseed.repositories;

import com.seed.databaseseed.entities.relationalModel.Entrepeneur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepeneurRepository extends JpaRepository<Entrepeneur, Integer> {
}
