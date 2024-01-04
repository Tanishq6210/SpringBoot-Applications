package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.JpaHibernateInDepthApplication;
import demo.jpahibernateindepth.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateInDepthApplication.class) //This will start the entire context so that all the tables and beans gets initialised, so that we can test
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        logger.info("{}", courseOptional.isPresent());
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        logger.info("{}", courseOptional.isPresent());
        assertFalse(courseOptional.isEmpty());
    }

    @Test
    public void sort() {
        Sort sortByName = Sort.by("name").ascending();
        List<Course> courses = courseSpringDataRepository.findAll(sortByName);
        logger.info("Courses -> {}", courses);
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());
        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());
    }

    @Test
    public void findUsingName() {
        logger.info("By name: {}", courseSpringDataRepository.findByName("tanishq"));
    }
}
