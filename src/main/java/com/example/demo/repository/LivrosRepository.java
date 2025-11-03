package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Estante;
import com.example.demo.model.Livro;
import java.util.List;

@Repository
public interface LivrosRepository extends JpaRepository<Livro,Integer>{

    // lista os livros que estão na estante referênciada
    List<Livro> findByEstante(Estante estante);
    // livros da estante por nome
    List<Livro> findByEstanteAndNomeContainingIgnoreCase(Estante estante, String nome);
    // livros da estante por autor
    List<Livro> findByEstanteAndAutorContainingIgnoreCase(Estante estante, String autor);
    // livros da estante por categoria
    List<Livro> findByEstanteAndCategoriaContainingIgnoreCase(Estante estante, String categoria);

    // livros da estante por ano
    List<Livro> findByEstanteAndAnoLessThan(Estante estante, int ano);
    List<Livro> findByEstanteAndAnoLessThanEqual(Estante estante, int ano);
    List<Livro> findByEstanteAndAno(Estante estante, int ano);
    List<Livro> findByEstanteAndAnoGreaterThanEqual(Estante estante, int ano);
    List<Livro> findByEstanteAndAnoGreaterThan(Estante estante, int ano);

    // livros da estante por preço
    List<Livro> findByEstanteAndPrecoLessThan(Estante estante, double preco);
    List<Livro> findByEstanteAndPrecoLessThanEqual(Estante estante, double preco);
    List<Livro> findByEstanteAndPreco(Estante estante, double preco);
    List<Livro> findByEstanteAndPrecoGreaterThanEqual(Estante estante, double preco);
    List<Livro> findByEstanteAndPrecoGreaterThan(Estante estante, double preco);

    // livros da estante por quantidade
    List<Livro> findByEstanteAndQuantidadeLessThan(Estante estante, int quantidade);
    List<Livro> findByEstanteAndQuantidadeLessThanEqual(Estante estante, int quantidade);
    List<Livro> findByEstanteAndQuantidade(Estante estante, int quantidade);
    List<Livro> findByEstanteAndQuantidadeGreaterThanEqual(Estante estante, int quantidade);
    List<Livro> findByEstanteAndQuantidadeGreaterThan(Estante estante, int quantidade);


}
