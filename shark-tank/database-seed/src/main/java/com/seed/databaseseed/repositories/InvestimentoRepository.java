package com.seed.databaseseed.repositories;

import com.seed.databaseseed.entities.relationalModel.Investimento;
import com.seed.databaseseed.entities.relationalModel.SharkProjetoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, SharkProjetoPK> {
}
