package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.entity.Course;
import demo.jpahibernateindepth.entity.Review;
import demo.jpahibernateindepth.entity.ReviewRating;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course save(Course course) {
        if(course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course); //To update
        }
        return course;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        /*
        Since, we are under @Transactional class, and we are saving a data in the db using EntityManager,
        The @Transactional will save each and every change happening to course to the db, even if the update (merge) method is
        not called explicitly.
         */
        Course course1 = new Course("New Course");
        Course course2 = new Course("Angular Course");
        /*
        The em.persist() method schedules an entity to be inserted into the database.
        However, the actual SQL statements to insert these entities are not executed immediately.
        They are queued to be executed at an appropriate time, typically when the transaction commits.
         */
        em.persist(course1);
        em.persist(course2);

        /*
        The em.flush() method is used to force synchronization of changes from the persistence context to the database,
        ensuring that any pending SQL statements are executed immediately within the current transaction.
         */
        em.flush();

//        em.detach(course2); //After this line, changes done to course2 will not be saved in the database, cuz object is no longer attached to entity manager
//        em.clear();// This will clear all the objects linked to entity manager. No changes after this will be saved to db automatically.

        /*
        This change of name will also be reflected in the database
         */
        course1.setName("Updated new course"); //This change will still be saved in the db
        course2.setName("Angular JS"); //Only this will not be saved, since course2 is detached

        /*
        This function will fetch the data from the database for course1,
        all changes done to course1 will be lost.
        For eg: course1 (Original name) = "New Course" , we saved this in db using flush
                After setting name, course1 name is "Updated new course"
                After refresh name will again be "New Course", previous update is lost
                After next flush no change will be done, because it is refreshed just now
         */

        /*
        This function will give error if not used after .flush() function, because persist will not save data in the db at that instant.
        So there will be no entry of course1 in the db then how will it fetch it from the db using refresh
         */
        em.refresh(course1);
        em.flush();

        Course c1 = new Course("Time Course");
        em.persist(c1);

        Course c2 = findById(10001L);
        c2.setName("Updated");
    }

    public void addHardCodedReviewsForCourse() {
        Course course = findById(10003L);
        logger.info("Course reviews -> {}", course.getReviews());

        //Add review to course
        Review review = new Review("BADD", ReviewRating.FIVE);
        course.addReview(review);
        review.setCourse(course);

        //Save to db
        em.persist(review);
        em.flush();
        logger.info("REVIEWS {}", findById(10003L).getReviews());
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("Course reviews -> {}", course.getReviews());

        for(Review review: reviews) {
            course.addReview(review);
            review.setCourse(course);

            //Save to db
            em.persist(review);
        }

        logger.info("REVIEWS {}", findById(10003L).getReviews());
    }
}
