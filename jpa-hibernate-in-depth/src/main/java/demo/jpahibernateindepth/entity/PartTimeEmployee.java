package demo.jpahibernateindepth.entity;

import jakarta.persistence.Entity;
import jakarta.servlet.http.Part;

import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{
    protected PartTimeEmployee() {}

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    public BigDecimal hourlyWage;
}
