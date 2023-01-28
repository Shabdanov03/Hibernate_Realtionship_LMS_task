package java8.service;

import java8.entity.Course;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface CourseService {
    String saveCourse(Course course);
    Optional<Course> getCourseById(Long courseId);
    List<Course> getAllCourse(); //(датасы боюнча сорттоп чыгаруу)
    String updateCourse(Long id,Course course);
    String deleteCourseById(Long courseId); //(курсту очургондо, курска assign болгон инструктор очпошу керек, курсун ичиндеги сабактар очуш керек)
    Optional<Course> getCourseByName(String name);
}
