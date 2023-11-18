package com.seed.databaseseed.repositories;

import com.seed.databaseseed.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
}
