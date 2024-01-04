package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateInDepthApplication.class) //This will start the entire context so that all the tables and beans gets initialised, so that we can test
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query =
                em.createNamedQuery("query_get_100_all_courses", Course.class);
        List<Course> result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query =
                em.createQuery("select c from Course c where name like '%100 steps'", Course.class);
        List<Course> result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }
}
