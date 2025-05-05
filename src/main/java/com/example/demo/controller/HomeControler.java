package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Estante;
import com.example.demo.model.Livro;
import com.example.demo.repository.EstanteRepository;
import com.example.demo.repository.LivrosRepository;

@Controller
@RequestMapping("/home")
public class HomeControler {

    @Autowired
    EstanteRepository estanteRepository;
    @Autowired
    LivrosRepository livrosRepository;

    @GetMapping("")
    public ModelAndView Principal(){
        ModelAndView mv = new ModelAndView("home");
        List<Estante> estantes = RetornarEstantes();
        mv.addObject("estantes", estantes);
        return mv;
    }

    public List<Estante> RetornarEstantes(){
        List<Estante> estantes = estanteRepository.findAll();
        for (Estante estante : estantes) {
            AtulizarEstante(estante);
        }
        estantes = estanteRepository.findAll();
        return estantes;
    }

    public void AtulizarEstante(Estante estante){
        List<Livro> livros = livrosRepository.findByEstante(estante);
        int soma = 0;
        for (Livro livro : livros) {
            soma += livro.getQuantidade();
        }
        estante.setNumero_livros(soma);
        estanteRepository.save(estante);
    }
}
