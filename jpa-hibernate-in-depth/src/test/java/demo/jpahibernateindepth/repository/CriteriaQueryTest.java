package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
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
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {

        //select c from Course c;

        //1 Use Criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicates etc to the criteria query
        //4. Add predicates etc to the criteria query
        //5. Build the typed query using entity manager and criteria query

        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void all_courses_having_100Steps() {
        //select c from Course c where name like '%100 Steps' ";

        //1 Use Criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicates etc to the criteria query
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        //4. Add predicates etc to the criteria query
        cq.where(like100Steps);

        //5. Build the typed query using entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void all_courses_without_students() {
        //select c from Course c where name like '%100 Steps' ";

        //1 Use Criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicates etc to the criteria query
        Predicate like100Steps = cb.isEmpty(courseRoot.get("students"));

        //4. Add predicates etc to the criteria query
        cq.where(like100Steps);

        //5. Build the typed query using entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void join() {
        //select c from Course c where name like '%100 Steps' ";

        //1 Use Criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicates etc to the criteria query
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add predicates etc to the criteria query


        //5. Build the typed query using entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }

    @Test
    public void left_join() {
        //select c from Course c where name like '%100 Steps' ";

        //1 Use Criteria builder to create a criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define predicates etc to the criteria query
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT); //Left join

        //4. Add predicates etc to the criteria query


        //5. Build the typed query using entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));
        List result = query.getResultList();
        logger.info("Select c from course c -> {}", result);
    }
}
