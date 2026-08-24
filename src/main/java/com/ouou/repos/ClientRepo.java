package com.ouou.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ouou.models.Client;

@Repository
	public interface ClientRepo extends JpaRepository<Client, Integer> {

	}


