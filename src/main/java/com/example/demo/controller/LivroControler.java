package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;


@Controller
@RequestMapping("/livro")
public class LivroControler {
    @Autowired
    LivroService livroService;

    // retorna página de Listar livros na estante referente ao id
    @GetMapping("/listar/{id}")
    public ModelAndView ListarLivrosNaEstante(
            @PathVariable("id") int id,
            @RequestParam(value = "pesquisa", required=false) String p,
            @RequestParam(value = "campo", required=false) String c,
            @RequestParam(value = "operador", required=false) String o
        ){
        return livroService.ListarLivrosNaEstante(id, p, c, o);
    }

    // retorna página de novo livro na estante referente ao id
    @GetMapping("/novo/{id}")
    public ModelAndView NovoLivro(@PathVariable("id") int id){
        return livroService.NovoLivro(id);
    }

    // salvar novo livro e redireciona para a página de listar livros na estante referente ao id
    @PostMapping("/novo")
    public String SalvarLivro(Livro livro, int estante_id, String preco_txt){
        livroService.SalvarLivro(livro, estante_id, preco_txt);
        String s = "redirect:/livro/listar/"+estante_id;
        return s;
    }
    
    // retorna página de editar livro referente ao id
    @GetMapping("/editar/{id}")
    public ModelAndView EditarLivro(@PathVariable("id") int id){
        return livroService.EditarLivro(id);
    }

    // salvar edição do livro e redireciona para a página de listar livros na estante referente ao id
    @PostMapping("/editar")
    public String EditarLivro(Livro livro, int estante_id, String preco_txt){
        livroService.EditarLivro(livro,estante_id, preco_txt);
        return "redirect:/livro/listar/"+estante_id;
    }

    // deletar livro e redireciona para a página de listar livros na estante referente ao id
    @GetMapping("/deletar/{id}")
    public String DeletarLivro(@PathVariable("id") int id){
        return livroService.DeletarLivro(id);
    }

}
