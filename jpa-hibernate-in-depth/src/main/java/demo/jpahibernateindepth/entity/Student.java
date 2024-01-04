package demo.jpahibernateindepth.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    /*
    This LAZY means, the details of the passport will be fetched if required.
    If we want to find student then it will not fetch passport
    as in it the query which hibernate will use be select * from student where student id = 1
    when required then the passport details will be fetched

    By default:
    Mappings ending with **ToOne are EAGER ly fetched
    and others are LAZY ily fetched
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    /*
    Since, Student is the owning side of the relationship that's why adding joining table info here
     */
    @JoinTable(name="STUDENT_COURSE",
        joinColumns = @JoinColumn(name = "STUDENT_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private List<Course> courses = new ArrayList<>();

    @Embedded
    private Address address;

    public Student() {

    }
    public Student(String name) {
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
