package com.ouou.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id ;
	@Column(name = "name")
	private String name ;
	@Column(name = "password")
	private String password ;
	

}
