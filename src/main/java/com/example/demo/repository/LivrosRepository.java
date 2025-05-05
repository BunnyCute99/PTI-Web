package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Livro;
@Repository
public interface LivrosRepository extends JpaRepository<Livro,Integer>{
    
}
