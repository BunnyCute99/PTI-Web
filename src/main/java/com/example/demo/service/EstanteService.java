package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estante;
import com.example.demo.repository.EstanteRepository;

@Service
public class EstanteService {

    @Autowired
    EstanteRepository estanteRepository;

    public void CriarEstante(Estante novEstante){
        novEstante.setNumero_livros(0);;
        estanteRepository.save(novEstante);
    }

    public Estante RetornarEstante(int id){
        Estante estante = estanteRepository.getReferenceById(id);
        return estante;
    }

    public void EditarEstante(Estante estante){
        estanteRepository.save(estante);
    }

    public void DeletarEstante(int id){
        estanteRepository.deleteById(id);
    }
    
}
