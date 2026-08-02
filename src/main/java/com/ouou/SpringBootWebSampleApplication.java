package com.ouou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;

@SpringBootApplication
public class SpringBootWebSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebSampleApplication.class, args);
        System.out.println("It's working");

}
	@Bean
	public ModelMapper modelMapper() {
	        return new ModelMapper();
	}
}
