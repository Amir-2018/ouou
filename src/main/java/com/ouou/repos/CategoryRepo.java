package com.ouou.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ouou.models.Categorie;

@Repository
	public interface CategoryRepo extends JpaRepository<Categorie, Integer> {

	}


