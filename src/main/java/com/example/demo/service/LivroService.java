package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Estante;
import com.example.demo.model.Livro;
import com.example.demo.repository.EstanteRepository;
import com.example.demo.repository.LivrosRepository;


@Service
public class LivroService {
    
    @Autowired
    LivrosRepository livrosRepository;
    @Autowired
    EstanteRepository estanteRepository;

    public void SalvarLivro(Livro livro, int estanteID){
        Estante estante = estanteRepository.getReferenceById(estanteID);
        livro.setEstante(estante);
        livrosRepository.save(livro);        
    }

    public String DeletarLivro(int id){
        Livro livro = livrosRepository.getReferenceById(id);
        livrosRepository.deleteById(id);
        return "redirect:/livro/listar/"+livro.getEstante().getId();
    }

    public void EditarLivro(Livro novoLivro, int estante_id){
        Estante estante = estanteRepository.getReferenceById(estante_id);
        novoLivro.setEstante(estante);
        livrosRepository.save(novoLivro);
    }

    public ModelAndView ListarLivrosNaEstante(int id){
        Optional<Estante> estante = estanteRepository.findById(id);
        ModelAndView mv = new ModelAndView();
        if (estante.isPresent()) {
            mv.setViewName("/livros/lista");
            List<Livro> livros = livrosRepository.findByEstante(estante.get());
            mv.addObject("livros", livros);
            mv.addObject("estante", estante.get());
        }else{
            mv.setViewName("redirect:/home");
        }
        
        return mv;        
    }

    public ModelAndView NovoLivro(int id){
        ModelAndView mv = new ModelAndView();
        Optional<Estante> estante = estanteRepository.findById(id);
        if (estante.isPresent()) {
            mv.setViewName("/livros/novo");
            mv.addObject("estante", estante.get());
        }else{
            mv.setViewName("redirect:/home");
        }
        return mv;
    }

    public ModelAndView EditarLivro(int id){
        ModelAndView mv = new ModelAndView();
        Optional<Livro> livro = livrosRepository.findById(id);
        if (livro.isPresent()) {
            mv.setViewName("/livros/editar");
            mv.addObject("livro", livro.get());
        }else{
            mv.setViewName("redirect:/home");
        }
        return mv;
    }
}
