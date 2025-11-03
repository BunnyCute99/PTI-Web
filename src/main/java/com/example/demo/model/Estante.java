package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int numero_livros;
    private double valor;

    @OneToMany(mappedBy = "estante")
    List<Livro> livros;


    public Estante() {
    }

    public Estante(String nome, int numero_livros) {
        this.nome = nome;
        this.numero_livros = numero_livros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getNumero_livros() {
        return numero_livros;
    }

    public void setNumero_livros(int numero_livros) {
        this.numero_livros = numero_livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    

    
    

    
    

    
    
}
