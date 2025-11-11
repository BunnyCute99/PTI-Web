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

    @Autowired
    EstanteService estanteService;

    // salvar novo livro e associa a estante referente ao id e converte o preço de String para double
    public void SalvarLivro(Livro livro, int estanteID, String preco_txt){
        Estante estante = estanteRepository.getReferenceById(estanteID);
        livro.setEstante(estante);
        livro.setPreco(Double.parseDouble(preco_txt.replace(",", ".")));
        livrosRepository.save(livro);
        estanteService.AtulizarEstante(estante);        
    }

    // deletar livro e redireciona para a página de listar livros na estante referente ao id
    public String DeletarLivro(int id){
        Livro livro = livrosRepository.getReferenceById(id);
        Estante estante = livro.getEstante();
        livrosRepository.deleteById(id);
        estanteService.AtulizarEstante(estante);
        
        return "redirect:/livro/listar/"+livro.getEstante().getId();
    }

    // salvar edição do livro e associa a estante referente ao id e converte o preço de String para double
    public void EditarLivro(Livro novoLivro, int estante_id, String preco_txt){
        Estante estante = estanteRepository.getReferenceById(estante_id);
        novoLivro.setEstante(estante);
        novoLivro.setPreco(Double.parseDouble(preco_txt.replace(",", ".")));
        livrosRepository.save(novoLivro);
    }

    public ModelAndView ListarLivrosNaEstante(int id , String pesquisa, String campo, String operador){
        ModelAndView mv = new ModelAndView();
        Optional<Estante> estante = estanteRepository.findById(id);
        if (estante.isPresent()) {
            mv.setViewName("/livros/lista");
            List<Livro> livros;
            if (pesquisa != null) {
                livros = FiltrarLivros(estante.get(), pesquisa, campo, operador);
                mv.addObject("pesquisa", pesquisa);
                mv.addObject("campo", campo);
                mv.addObject("operador", operador);
            }else{
                livros = livrosRepository.findByEstante(estante.get());
            }
            mv.addObject("livros", livros);
            mv.addObject("estante", estante.get());
        }else{
            mv.setViewName("redirect:/home");
        }
        return mv;
    }

    public List<Livro> FiltrarLivros(Estante estante, String pesquisa, String campo, String operador){
        List<Livro> livros;
        switch (campo) {
            case "nome":
                livros = livrosRepository.findByEstanteAndNomeContainingIgnoreCase(estante, pesquisa);
            break;

            case "autor":
                livros = livrosRepository.findByEstanteAndAutorContainingIgnoreCase(estante, pesquisa);
            break;

            case "categoria":
                livros = livrosRepository.findByEstanteAndCategoriaContainingIgnoreCase(estante, pesquisa);
            break;

            case "ano":
                int ano = 0;
                try {
                    ano = Integer.parseInt(pesquisa);
                } catch (NumberFormatException e) {
                    ano = 0;
                }
                switch (operador) {
                    case "lt":
                        livros = livrosRepository.findByEstanteAndAnoLessThan(estante, ano);
                    break;

                    case "lte":
                        livros = livrosRepository.findByEstanteAndAnoLessThanEqual(estante, ano);
                    break;

                    case "eq":
                        livros = livrosRepository.findByEstanteAndAno(estante, ano);
                    break;

                    case "gte":
                        livros = livrosRepository.findByEstanteAndAnoGreaterThanEqual(estante, ano);
                    break;

                    case "gt":
                        livros = livrosRepository.findByEstanteAndAnoGreaterThan(estante, ano);
                    break;
                    
                    default:
                        livros = livrosRepository.findByEstante(estante);
                    break;
                }

            break;

            case "preço":
                double preco = 0;
                try {
                    preco = Double.parseDouble(pesquisa.replace(",", "."));
                } catch (NumberFormatException e) {
                    preco = 0;
                }
                switch (operador) {
                    case "lt":
                        livros = livrosRepository.findByEstanteAndPrecoLessThan(estante, preco);
                    break;

                    case "lte":
                        livros = livrosRepository.findByEstanteAndPrecoLessThanEqual(estante, preco);
                    break;

                    case "eq":
                        livros = livrosRepository.findByEstanteAndPreco(estante, preco);
                    break;

                    case "gte":
                        livros = livrosRepository.findByEstanteAndPrecoGreaterThanEqual(estante, preco);
                    break;

                    case "gt":
                        livros = livrosRepository.findByEstanteAndPrecoGreaterThan(estante, preco);
                    break;
                    
                    default:
                        livros = livrosRepository.findByEstante(estante);
                    break;
                }
            break;

            case "quantidade":
                int quantidade = 0;
                try {
                    quantidade = Integer.parseInt(pesquisa);
                } catch (NumberFormatException e) {
                    quantidade = 0;
                }
                switch (operador) {
                    case "lt":
                        livros = livrosRepository.findByEstanteAndQuantidadeLessThan(estante, quantidade);
                    break;

                    case "lte":
                        livros = livrosRepository.findByEstanteAndQuantidadeLessThanEqual(estante, quantidade);
                    break;

                    case "eq":
                        livros = livrosRepository.findByEstanteAndQuantidade(estante, quantidade);
                    break;

                    case "gte":
                        livros = livrosRepository.findByEstanteAndQuantidadeGreaterThanEqual(estante, quantidade);
                    break;

                    case "gt":
                        livros = livrosRepository.findByEstanteAndQuantidadeGreaterThan(estante, quantidade);
                    break;
                    
                    default:
                        livros = livrosRepository.findByEstante(estante);
                    break;
                }
            break;
        
            default:
                livros = livrosRepository.findByEstante(estante);
            break;
        }
        return livros;
    }

    // retorna página de novo livro na estante referente ao id ou redireciona para home se a estante não existir
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

    // retorna página de editar livro referente ao id ou redireciona para home se o livro não existir
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
