package com.projetogithub.dsmovie.repositories;
import com.projetogithub.dsmovie.entities.Score;
import com.projetogithub.dsmovie.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    //Padrão do JPA se você colocar o findBy + o nome do campo
    // Método que busca usuário por e-mail

    User findByEmail(String email);

}
