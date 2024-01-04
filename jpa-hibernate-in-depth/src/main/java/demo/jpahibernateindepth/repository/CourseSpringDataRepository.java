package demo.jpahibernateindepth.repository;

import demo.jpahibernateindepth.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameAndId(String name, Long id);
    List<Course> findByName(String name);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);

    @Query("select c from Course c where name like '%100 Steps'")
    List<Course> courseWith100StepsInName();

    @Query(value = "select c from Course c where name like '%100 Steps'",
    nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery();

    @Query(name="query_get_100_all_courses")
    List<Course> courseWith100StepsInNameUsingNamedQuery();
}
