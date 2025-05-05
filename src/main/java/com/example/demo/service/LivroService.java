package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Livro;
import com.example.demo.repository.LivrosRepository;

@Service
public class LivroService {
    
    @Autowired
    LivrosRepository livrosRepository;

    public void SalvarLivro(Livro livro){
        livrosRepository.save(livro);        
    }

    public void DeletarLivro(int id){
        livrosRepository.deleteById(id);
    }

    public void EditarLivro(Livro novoLivro){
        livrosRepository.save(novoLivro);
    }
}
