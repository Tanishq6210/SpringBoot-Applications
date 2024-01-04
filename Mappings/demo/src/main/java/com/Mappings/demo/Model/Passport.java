package com.Mappings.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Passport {
    @Id
    private Integer pass_id;
    private String passportNumber;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Integer getPass_id() {
        return pass_id;
    }

    public void setPass_id(Integer pass_id) {
        this.pass_id = pass_id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
