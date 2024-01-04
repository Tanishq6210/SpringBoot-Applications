package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
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
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    public void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @Transactional
    public void findById_firstLevelCachingDemo() {
        Course course = repository.findById(10001L);
        logger.info("First Course {}", course);

        /*
        Because of @Transactional course 10001L is cached, so the query is just executed once
         */
        Course course1 = repository.findById(10001L);
        logger.info("First Course again {}", course1);
    }

    /*
        This @DirtiesContext annotation resets the data, so that the data remains as it is after the execution of the test.
        For eg: Ig we delete the 10002L from the db then other test will fail if we try to retrieve the same id, because
        it will not exist in the db.
        After using this annotation, the id will be inserted again
     */
    @Test
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(10001L);
        assertNull(repository.findById(10001L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());
        course.setName("Updated");
        assertEquals("Updated", repository.save(course).getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }
}
