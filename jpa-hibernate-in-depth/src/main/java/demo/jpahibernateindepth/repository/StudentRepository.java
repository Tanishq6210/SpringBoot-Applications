package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.entity.Address;
import demo.jpahibernateindepth.entity.Course;
import demo.jpahibernateindepth.entity.Passport;
import demo.jpahibernateindepth.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Student save(Student student) {
        if(student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student); //To update
        }
        return student;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student student = new Student("Tanishq Tyagi");
        student.setPassport(passport);
        passport.setStudent(student);
        student.setAddress(new Address("abc", "ac", "SRE"));

        em.persist(student);
        em.flush();
    }

    /*
    @Transactional is written on class
     */
    public void someOperationToUnderstandPersistenceContext() {
        /*
        If we don't annotate with @Transactional then the entity manager will start
        a transaction when .find() is called and will be terminated when the data is fetched
         */
        Student student = em.find(Student.class, 20001L);
        //Persistence Context(student)

        Passport passport = student.getPassport();
        //Persistence Context(student, passport)

        passport.setNumber("E123456");
        //Persistence Context(student, passport++)

        student.setName("Blo the pp updated");
        //Persistence Context(student++, passport)
    }

    public void insertHardCodedStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("Microservices");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        /*
        The second em.persist(student) and em.persist(course) might be redundant since the entities are already managed by the persistence context after the initial persist calls.
These redundant calls might not cause errors, but they might trigger unnecessary database interactions.
         */
        em.persist(student);
        em.persist(course);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        /*
        The second em.persist(student) and em.persist(course) might be redundant since the entities are already managed by the persistence context after the initial persist calls.
These redundant calls might not cause errors, but they might trigger unnecessary database interactions.
         */
        em.persist(student);
        em.persist(course);
    }
}
