package demo.jpahibernateindepth.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.util.List;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //All columns in a single table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Each concrete class has its own table
//@Inheritance(strategy = InheritanceType.JOINED) //Common columns in employee table, other table will use id of employee as foreign key in their table
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    public Employee() {

    }
    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
