package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Estante;
import com.example.demo.service.EstanteService;

@Controller
@RequestMapping("/home")
public class HomeControler {

    @Autowired
    EstanteService estanteService;

    // retorna a pagina principal com todas as estantes ou com o resultado da pesquisa
    @GetMapping("")
    public ModelAndView Principal(@RequestParam(value = "pesquisa", required=false) String p){
        ModelAndView mv = new ModelAndView("home");
        List<Estante> estantes;
        if (p != null) {
            estantes = estanteService.ProcurarEstantes(p);
            mv.addObject("pesquisa", p);
        }else{
            estantes = estanteService.RetornarEstantes();
        }
        mv.addObject("estantes", estantes);
        return mv;
    }

}
