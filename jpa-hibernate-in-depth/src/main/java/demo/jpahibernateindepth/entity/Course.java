package demo.jpahibernateindepth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
//This Named Query import is required

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQueries(
        value = {
                @NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
                @NamedQuery(name = "query_get_100_all_courses", query = "select c from Course c where name like '%100 steps'"),
                @NamedQuery(name = "query_get_all_courses_join_fetch", query = "select c from Course c join fetch c.students s")
        }
)
@javax.persistence.Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id = ?")
@Where(clause = "is_deleted=false")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    public Course() {

    }

    //This will get called as soon as a row is deleted
    //so that the cache data also gets updated along with the table
    @javax.persistence.PreRemove
    private void preRemove() {
        this.isDeleted = true;
    }

    public Course(String name) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
