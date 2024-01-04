package com.Mappings.demo.DTO;

// import com.Mappings.demo.Model.Passport;

// import com.Mappings.demo.Model.Passport;
// import com.Mappings.demo.Model.Student;

public class StudentDto {
    private Integer id;

    private String name;
    private PassportDto passport;

    public StudentDto() {

    }

    public StudentDto(Integer id, String name, PassportDto passport) {
        super();
        this.id = id;
        this.name = name;
        this.passport = passport;
    }

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

    public PassportDto getPassport() {
        return passport;
    }

    public void setPassport(PassportDto passport) {
        this.passport = passport;
    }
}
