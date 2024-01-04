package com.Mappings.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mappings.demo.Model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
