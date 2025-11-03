package com.example.demo.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estante;
import com.example.demo.model.Livro;
import com.example.demo.repository.EstanteRepository;
import com.example.demo.repository.LivrosRepository;

@Service
public class EstanteService {

    @Autowired
    EstanteRepository estanteRepository;
    @Autowired
    LivrosRepository livrosRepository;

    // criar nova estante e seta numero de livros como 0
    public void CriarEstante(Estante novEstante){
        novEstante.setNumero_livros(0);;
        estanteRepository.save(novEstante);
    }

    // retorna estante referente ao id
    public Estante RetornarEstante(int id){
        Estante estante = estanteRepository.getReferenceById(id);
        return estante;
    }

    // editar estante
    public void EditarEstante(Estante estante){
        estanteRepository.save(estante);
    }

    // deleta estante somente se o numero de livros for 0 para evitar erros de referencia
    public void DeletarEstante(int id){
        Estante estante = estanteRepository.getReferenceById(id);
        if (estante.getNumero_livros() == 0) {
            estanteRepository.deleteById(id);
        }
    }

    // retorna todas as estantes e atualiza o numero de livros e o valor total dos livros em cada estante
    // ordena as estantes alfabeticamente
    public List<Estante> RetornarEstantes(){
        List<Estante> estantes = estanteRepository.findAll();
        for (Estante estante : estantes) {
            AtulizarEstante(estante);
        }
        estantes = estanteRepository.findAll();
        estantes.sort(Comparator.comparing(Estante::getNome,String.CASE_INSENSITIVE_ORDER));
        return estantes;
    }

    // atualiza o numero de livros em uma estante e o valor total dos livros
    public void AtulizarEstante(Estante estante){
        List<Livro> livros = livrosRepository.findByEstante(estante);
        int soma = 0;
        double total = 0;
        for (Livro livro : livros) {
            soma += livro.getQuantidade();
            total += livro.getQuantidade() * livro.getPreco();
        }
        estante.setNumero_livros(soma);
        estante.setValor(total);
        estanteRepository.save(estante);
    }

    // procura estantes pelo nome contendo o parametro p ignorando maiusculas e minusculas e ordena alfabeticamente
    public List<Estante> ProcurarEstantes(String p){
        List<Estante> estantes = estanteRepository.findByNomeContainingIgnoreCase(p);
        estantes.sort(Comparator.comparing(Estante::getNome,String.CASE_INSENSITIVE_ORDER));
        return estantes;
    }
    
}
