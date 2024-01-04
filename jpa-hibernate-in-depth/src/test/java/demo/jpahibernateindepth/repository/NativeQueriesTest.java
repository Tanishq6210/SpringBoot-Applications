package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateInDepthApplication.class) //This will start the entire context so that all the tables and beans gets initialised, so that we can test
@Transactional
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("select * from COURSE where is_deleted=0", Course.class);
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void native_queries_with_parameter() {
        Query query = em.createNativeQuery("select * from COURSE where id = ? and  where is_deleted=0", Course.class);
        query.setParameter(1, 10001L);
        List result = query.getResultList();
        logger.info("Select c from course c where id = 10001 -> {}", result);
    }

    @Test
    public void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("select * from COURSE where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List result = query.getResultList();
        logger.info("Select c from course c where id = :id -> {}", result);
    }

    @Test
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("update course set last_updated_date=now()", Course.class);
        int noOfRows = query.executeUpdate();
        logger.info("No. of rows updated are -> {}", noOfRows);
    }
}
