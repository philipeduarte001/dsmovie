package com.projetogithub.dsmovie.controllers;
import com.projetogithub.dsmovie.dto.MovieDTO;
import com.projetogithub.dsmovie.dto.ScoreDTO;
import com.projetogithub.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService service;


    // A pagina do formulário vai passar os atributos do SCOREDTO como requisição para o Banco de Dados
    // RequestBody = sinalizar para o Spring pra ele pegar do JSON e converter para o Score DTO

    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
        MovieDTO movieDTO = service.saveScore(dto);
        return movieDTO;
    }

}
