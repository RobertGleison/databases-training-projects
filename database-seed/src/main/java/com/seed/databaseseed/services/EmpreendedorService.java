package com.seed.databaseseed.services;

import com.seed.databaseseed.repositories.EmpreendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpreendedorService {

    @Autowired
    private EmpreendedorRepository repository;
}
