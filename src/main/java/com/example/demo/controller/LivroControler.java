package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;


@Controller
@RequestMapping("/livro")
public class LivroControler {
    @Autowired
    LivroService livroService;

    @GetMapping("/listar/{id}")
    public ModelAndView ListarLivrosNaEstante(@PathVariable("id") int id){
        return livroService.ListarLivrosNaEstante(id);
    }

    @GetMapping("/novo/{id}")
    public ModelAndView NovoLivro(@PathVariable("id") int id){
        return livroService.NovoLivro(id);
    }

    @PostMapping("/novo")
    public String SalvarLivro(Livro livro, int estante_id){
        livroService.SalvarLivro(livro, estante_id);
        String s = "redirect:/livro/listar/"+estante_id;
        return s;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView EditarLivro(@PathVariable("id") int id){
        return livroService.EditarLivro(id);
    }

    @PostMapping("/editar")
    public String EditarLivro(Livro livro, int estante_id){
        livroService.EditarLivro(livro,estante_id);
        return "redirect:/livro/listar/"+estante_id;
    }

    @GetMapping("/deletar/{id}")
    public String DeletarLivro(@PathVariable("id") int id){
        return livroService.DeletarLivro(id);
    }

}
