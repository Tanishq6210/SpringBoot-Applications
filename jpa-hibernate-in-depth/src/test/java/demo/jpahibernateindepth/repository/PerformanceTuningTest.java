package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateInDepthApplication.class) //This will start the entire context so that all the tables and beans gets initialised, so that we can test
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();

        for(Course course: courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem() {
        /*
        This is done to load both the students and course at the same time, only
        for this query as it's a lazy fetch but only for this one it will fetch
        by joining the table
         */
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        for(Course course: courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solutionOfNPlusOneProblem() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();

        for(Course course: courses) {
            logger.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }
}
