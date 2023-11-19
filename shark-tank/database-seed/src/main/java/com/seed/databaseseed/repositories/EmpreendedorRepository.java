package com.seed.databaseseed.repositories;

import com.seed.databaseseed.entities.relationalModel.Empreendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpreendedorRepository extends JpaRepository<Empreendedor, Integer> {
}
