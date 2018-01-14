package com.example.Fibonacci.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FRepo extends JpaRepository<FNumber, Integer> {
    @Query(value = "SELECT id, number, value, value2 " +
                    "FROM fnumber " +
                    "Where id=1 for update", nativeQuery = true)
    FNumber getValues();
}
