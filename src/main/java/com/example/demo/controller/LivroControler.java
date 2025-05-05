package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroControler {
    @Autowired
    LivroService livroService;

    @GetMapping("/novo")
    public String NovoLivro(Livro livro){
        livroService.SalvarLivro(livro);
        return "livros/novo";
    }
}
