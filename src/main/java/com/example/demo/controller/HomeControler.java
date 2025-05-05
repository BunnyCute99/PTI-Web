package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Estante;
import com.example.demo.repository.EstanteRepository;

@Controller
@RequestMapping("/home")
public class HomeControler {

    @Autowired
    EstanteRepository estanteRepository;

    @GetMapping("")
    public ModelAndView Principal(){
        ModelAndView mv = new ModelAndView("home");
        List<Estante> estantes = estanteRepository.findAll();
        mv.addObject("estantes", estantes);
        return mv;
    }
}
