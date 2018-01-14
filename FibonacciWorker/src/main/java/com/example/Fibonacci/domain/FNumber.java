package com.example.Fibonacci.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name="FNumber")
public class FNumber {

    @Id
    private int id;
    private int number;
    private BigInteger value;
    private BigInteger value2;

    public FNumber() {

    }

    public FNumber(int id, int number, BigInteger value) {
        this.id = id;
        this.number = number;
        this.value = value;
        this.value2 = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigInteger getValue() {
        return value;
    }

    public BigInteger getValue2() { return value2; }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public void updateValues(BigInteger value, BigInteger value2) {
        this.value = value;
        this.value2 = value2;
    }


}
