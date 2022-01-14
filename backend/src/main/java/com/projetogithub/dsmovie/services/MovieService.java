package com.projetogithub.dsmovie.services;

import com.projetogithub.dsmovie.dto.MovieDTO;
import com.projetogithub.dsmovie.entities.Movie;
import com.projetogithub.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

// Registro de compenente para mapear para o Spring Boot
@Service
public class MovieService {

        // para usar movierepository precisa fazer uma composição de componentes
        // Vai gerenciar e instanciar os objeto do movierepository - Autowired
        @Autowired
        private MovieRepository repository;

        // O retorno do método será sempre DTO (se houver DTO) = <MovieDTO> = Comunicação do Service com o Controller
        // o retorno do método será encaminhado para o Controller
        // movierepository = objeto necessário para acessar o bd
        // returno do repository é uma entidade = Movie <MovieDTO> através da contrutor que recebe a entidade como parâmetro
        // Busca paginada: Pageable pageable = objeto especial: page
        // ** Função de alta ordem (Converter a entidade (movie) para o MovieDTO)
        // ** Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
        // ** map vai aplicar uma função a cada elemento da lista
        // ** x = apelido da lista
        // ** para cada elemento da lista eu quero um new movieDTO de x
        // Transaction = Essa anotação vai garantir que esse método vai resolver tudo que for da JPA
        // readOnly = true = informar que é um método somente de leitura (eficiência no database)

        @Transactional(readOnly = true)
        public Page<MovieDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        Page<Movie> result = repository.findAll(pageable);
        Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
        return page;
        }

        // FindByID returna um objeto opcional
        // Para acessar o objeto "movie" que está dentro dele você coloca o get
        // Instanciar a classe MovieDTO e vai receber como parâmetro o result
        @Transactional(readOnly = true)
        public MovieDTO findById(Long id) {
                Movie result = repository.findById(id).get();
                MovieDTO dto = new MovieDTO(result);
                return dto;
        }




}
