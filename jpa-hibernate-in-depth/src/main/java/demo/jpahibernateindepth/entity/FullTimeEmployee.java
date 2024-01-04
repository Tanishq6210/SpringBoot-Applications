package demo.jpahibernateindepth.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class FullTimeEmployee extends Employee{
    public BigDecimal salary;

    protected FullTimeEmployee() {}

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
