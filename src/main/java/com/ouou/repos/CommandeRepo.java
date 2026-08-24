package com.ouou.repos;

import com.ouou.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepo extends JpaRepository<Commande, Integer> {

}


