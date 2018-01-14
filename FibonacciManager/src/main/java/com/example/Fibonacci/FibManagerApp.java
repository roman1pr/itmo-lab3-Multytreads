package com.example.Fibonacci;

import com.example.Fibonacci.domain.FNumber;
import com.example.Fibonacci.domain.FRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;

@SpringBootApplication
public class FibManagerApp {

	@Autowired
	private FRepo fRepo;

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner cmr(){
		return (args) -> {
            FNumber first = new FNumber(1, 1, BigInteger.valueOf(1), BigInteger.valueOf(1));
			fRepo.save(first);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(FibManagerApp.class, args);
	}
}
