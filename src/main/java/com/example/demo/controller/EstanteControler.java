package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Estante;
import com.example.demo.service.EstanteService;

@Controller
@RequestMapping("/estante")
public class EstanteControler {

    @Autowired
    EstanteService estanteService;

    @Autowired
    HomeControler homeControler;
    
    // retorna a pagina para criar uma nova estante
    @GetMapping("/novo")
    public String NovaEstante(){
        return "estante/novo";
    }

    // cria uma nova estante e redireciona para a pagina principal
    @PostMapping("/novo")
    public String CriarEstante(Estante novEstante){
        estanteService.CriarEstante(novEstante);
        return "redirect:/home";
    }

    // retorna a pagina para editar uma estante existente
    @GetMapping("/editar/{id}")
    public ModelAndView EditarEstante(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("estante/editar");
        mv.addObject("estante", estanteService.RetornarEstante(id));
        return mv;
    }

    // edita a estante e redireciona para a pagina principal
    @PostMapping("/editar")
    public String EditarEstante(Estante estante){
        estanteService.EditarEstante(estante);
        return "redirect:/home";
    }

    // deleta a estante e redireciona para a pagina principal
    @GetMapping("/deletar/{id}")
    public String DeletarEstante(@PathVariable("id") int id){
        estanteService.DeletarEstante(id);
        return "redirect:/home";
    }
}
