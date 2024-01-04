package com.Mappings.demo.Model;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Data
@Entity
public class Student {
    @Id
    private Integer id;
    private String name;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Passport passport;

    // public Student() {
    // this.passport = null;
    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
