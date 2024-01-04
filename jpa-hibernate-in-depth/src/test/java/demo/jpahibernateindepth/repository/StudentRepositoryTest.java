package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Passport;
import demo.jpahibernateindepth.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateInDepthApplication.class) //This will start the entire context so that all the tables and beans gets initialised, so that we can test
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    /*
    Using transactional the hibernate session with the db will be terminated at the end of the method.
    Earlier it was getting terminated as soon as the em.find() was ended.
    & passport was lazily fetched therefore student didn't have the passport
    Now when getPassport is executed the db will fire a new query to get it.
     */
    @Transactional
    public void retrieveStudentAndPassport() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }


    @Test
    @Transactional
    public void retrievePasswordAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student -> {}", passport.getStudent());
    }


    @Test
    /*
    If one operation within a transaction fails then all previous operations are reverted
     */
//    @Transactional //Persistence Context
    public void someTest() {
        repository.someOperationToUnderstandPersistenceContext();
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Student.getCourses() -> {}", student.getCourses());
    }
}
