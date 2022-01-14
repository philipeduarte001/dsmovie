package com.projetogithub.dsmovie.repositories;
import com.projetogithub.dsmovie.entities.Movie;
import com.projetogithub.dsmovie.entities.Score;
import com.projetogithub.dsmovie.entities.ScorePK;
import com.projetogithub.dsmovie.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
