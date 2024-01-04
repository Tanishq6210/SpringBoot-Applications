package com.Mappings.demo.Service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mappings.demo.DTO.PassportDto;
import com.Mappings.demo.DTO.StudentDto;
import com.Mappings.demo.Model.Passport;
import com.Mappings.demo.Model.Student;
import com.Mappings.demo.Repository.StudentRepo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepo studentRepo, ModelMapper modelMapper) {
        super();
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    public StudentDto extractedStudentDTO(Student st) {
        PassportDto p = new PassportDto(st.getPassport().getPass_id(), st.getPassport().getPassportNumber());
        StudentDto stu = new StudentDto(st.getId(), st.getName(), extractedPassportDto(st.getPassport()));
        // p.setStudent(stu);
        // stu.setId(st.getId());
        // stu.setName(st.getName());
        // PassportDto p = extractedPassportDto(st.getPassport());
        // stu.setPassport(p);
        return stu;
        // return this.modelMapper.map(st, StudentDto.class);
    }

    public Student extractedStudent(StudentDto s) {

        return this.modelMapper.map(s, Student.class);
    }

    public Passport extractedPassport(PassportDto pt) {
        return modelMapper.map(pt, Passport.class);
    }

    public PassportDto extractedPassportDto(Passport p) {

        return this.modelMapper.map(p, PassportDto.class);
    }

    public StudentDto add(StudentDto student) {
        Student studentEntity = extractedStudent(student);
        if (student.getPassport() != null) {
            Passport passportEntity = extractedPassport(student.getPassport());
            studentEntity.setPassport(passportEntity);
            passportEntity.setStudent(studentEntity);
        } else {
            studentEntity.setPassport(null);
        }

        studentEntity = studentRepo.save(studentEntity); // Save the student and get the updated entity
        return student;
    }

    public StudentDto getAll(Integer id) {
        // List<Student> laptops = this.studentRepo.findAll();
        // List<StudentDto> Users;
        // for (Student student : laptops) {

        // }
        Student st = studentRepo.findById(8).get();
        return extractedStudentDTO(st);
    }
}
