package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Estante;

@Repository
public interface EstanteRepository extends JpaRepository<Estante,Integer>{

    // metódo para buscar estantes pelo nome, ignorando maiúsculas e minúsculas
    List<Estante> findByNomeContainingIgnoreCase(String nome);
    
}
