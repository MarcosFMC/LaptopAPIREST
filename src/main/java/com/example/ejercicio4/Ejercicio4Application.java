package com.example.ejercicio4;

import com.example.ejercicio4.entity.Laptop;
import com.example.ejercicio4.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicio4Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ejercicio4Application.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null,"Hp","a331d","58484");

		Laptop laptop2 = new Laptop(null,"MacOs","e223d","521234");

		repository.save(laptop1);
		repository.save(laptop2);
	}

}
