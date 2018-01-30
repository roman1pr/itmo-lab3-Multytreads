package com.example.Fibonacci;

import com.example.Fibonacci.domain.FNumber;
import com.example.Fibonacci.domain.FRepo;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.sql.SQLException;

import com.mysql.jdbc.MysqlIO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@SpringBootApplication
public class FibManagerApp {

	@Autowired
	private FRepo fRepo;

	@Bean
	public RestTemplate restTemplate(){
		System.out.println();
		System.out.println("restTemplate");
		System.out.println();
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner cmr(){
		System.out.println();
		System.out.println("cmr");
		System.out.println();
		try {
			return (args) -> {
				FNumber first = new FNumber(1, 1, BigInteger.valueOf(1), BigInteger.valueOf(1));
				fRepo.save(first);
			};
		} catch(RuntimeException rte) {
			System.out.println("Failed to retrieve department :" + rte.getMessage());
		}
		return null;
	}


	public static void main(String[] args) {
		try {
			SpringApplication.run(FibManagerApp.class, args);
		} catch (HibernateException rte){
			System.out.println("Failed to retrieve department :" + rte.getMessage());
		}
	}
}
