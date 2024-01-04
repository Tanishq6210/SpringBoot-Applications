package com.Mappings.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.Mappings.demo.DTO.PassportDto;
import com.Mappings.demo.DTO.StudentDto;

import com.Mappings.demo.Service.StudentService;

@RestController
public class AppController {
    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public StudentDto addStudent(@RequestBody StudentDto student) {

        return studentService.add(student);
    }

    @GetMapping("/all")
    public StudentDto getAll() {
        return studentService.getAll(8);
    }
}
