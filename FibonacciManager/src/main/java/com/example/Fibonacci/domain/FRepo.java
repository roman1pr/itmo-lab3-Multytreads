package com.example.Fibonacci.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FRepo extends JpaRepository<FNumber, Integer> {
}
