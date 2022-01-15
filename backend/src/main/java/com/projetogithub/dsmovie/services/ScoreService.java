package com.projetogithub.dsmovie.services;

import com.projetogithub.dsmovie.dto.MovieDTO;
import com.projetogithub.dsmovie.dto.ScoreDTO;
import com.projetogithub.dsmovie.entities.Movie;
import com.projetogithub.dsmovie.entities.Score;
import com.projetogithub.dsmovie.entities.User;
import com.projetogithub.dsmovie.repositories.MovieRepository;
import com.projetogithub.dsmovie.repositories.ScoreRepository;
import com.projetogithub.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ScoreService {

        @Autowired
        private MovieRepository movierepository;

        @Autowired
        private ScoreRepository scoreRepository;

        @Autowired
        private UserRepository userRepository;


        @Transactional

        // User é uma variável que vai receber o e-mail do usuário que chegou pelo DTO
        // Se o usuário não existe tem que inserir no database
        // se o o user for nulo (ele não existe no database
        // então cria um novo usuário e seta o e-mail
        // depois vai salvar no banco com JPA
        // saveAndFlush = salva o objeto atualizado no database
        public MovieDTO saveScore(ScoreDTO dto) {
              User user = userRepository.findByEmail(dto.getEmail());
               if (user == null) {
                   user = new User();
                   user.setEmail(dto.getEmail());
                   user = userRepository.saveAndFlush(user);
               }
               // findById retorna sempre um objeto que precisa ser pego pelo get para pegar o objeto dentro dele
              // busca por id sempre vai retornar um optional e precisa colocar o get.
               Movie movie = movierepository.findById(dto.getMovieId()).get();

               //preparação de um objeto em memória para receber os três dados
               // movie, user e score
               // próximo passo: salvar no database
               // saveAndFlush = vai salvar no banco e vai trazer os resdultados
            Score score = new Score();
         score.setMovie(movie);
         score.setUser(user);
         score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);


            // variável do tipo double (sum) recebe 0.0;
            // for para percorrer todos os scores de um determinado filme
            // O for vai percorrer cada score (movie.getScores) e colocando o nome dele de "s" para cada elemento
            // no final a variável sum vai somar o valor dela + o valor das avaliações
            // variável para calcular a soma (sum) de todos os valores
            //getScors = é o valor da cada score
            double sum = 0.0;
            for (Score s : movie.getScores()) {
            sum = sum + s.getValue();

        }

            // média das avaliações dos usuários: soma das avaliações dividida pela quantidade de usuários
            // variável do tipo double recebe a variável o valor dela dividido pela quantidade
            // quantidade é o tamanho do conjunto movie.getScores
            // size é um métido que pega o tamanho da coleção
            double avg = sum / movie.getScores().size();

            // salvar o score (média) na tabela
            // avg é a soma de todo mundo dividido pela quantidade
            //
            movie.setScore(avg);
            movie.setCount(movie.getScores().size());

            movie = movierepository.save(movie);

        return new MovieDTO(movie);

        }


}
