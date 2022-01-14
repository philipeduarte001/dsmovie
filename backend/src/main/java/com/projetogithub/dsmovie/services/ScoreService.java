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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
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

        public MovieDTO saveScore(ScoreDTO dto) {
              User user = userRepository.findByEmail(dto.getEmail());
               if (user == null) {
                   user = new User();
                   user.setEmail(dto.getEmail());
                   user = userRepository.saveAndFlush(user);
               }

        Movie movie = movierepository.findById(dto.getMovieId()).get();

         Score score = new Score();
         score.setMovie(movie);
         score.setUser(user);
         score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);





            double sum = 0.0;
            for (Score s : movie.getScores()) {
            sum = sum + s.getValue();

        }


            double avg = sum / movie.getScores().size();

            movie.setScore(avg);
            movie.setCount(movie.getScores().size());

            movie = movierepository.save(movie);

        return new MovieDTO(movie);

        }


}
