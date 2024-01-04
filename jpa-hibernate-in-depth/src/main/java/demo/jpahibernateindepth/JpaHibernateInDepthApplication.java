package demo.jpahibernateindepth;

import demo.jpahibernateindepth.entity.*;
import demo.jpahibernateindepth.repository.CourseRepository;
import demo.jpahibernateindepth.repository.EmployeeRepository;
import demo.jpahibernateindepth.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class JpaHibernateInDepthApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateInDepthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		logger.info("Record: {}", courseRepository.findById(10001L));
////		courseRepository.deleteById(10002L);
//		Course course = new Course("Course 101");
//		logger.info("Inserted: {}", courseRepository.save(course));
//		course.setName("Course 102");
//		logger.info("Inserted: {}", courseRepository.save(course));
//		courseRepository.playWithEntityManager();
//		studentRepository.saveStudentWithPassport();
//		courseRepository.addHardCodedReviewsForCourse();

		/*
		Review review1 = new Review("Best Meow", "5");
		Review review2 = new Review("Best Meowyyy", "5");
		courseRepository.addReviewsForCourse(10003L, List.of(review1, review2));
		studentRepository.insertHardCodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("Harry Potter"), new Course("Hogwarts"));
		*/
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

//		logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());
		logger.info("PartTimeEmployees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("FullTimeEmployees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
	}
}
