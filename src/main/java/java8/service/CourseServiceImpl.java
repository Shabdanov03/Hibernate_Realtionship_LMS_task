package java8.service;

import java8.entity.Course;
import java8.repository.CourseRepository;
import java8.repository.CourseRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class CourseServiceImpl implements CourseService{
    CourseRepository courseRepository = new CourseRepositoryImpl();

    @Override
    public String saveCourse(Course course) {
        return courseRepository.saveCourse(course);
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.getAllCourse();
    }

    @Override
    public String updateCourse(Long id, Course course) {
        return courseRepository.updateCourse(id,course);
    }

    @Override
    public String deleteCourseById(Long courseId) {
        return courseRepository.deleteCourseById(courseId);
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return courseRepository.getCourseByName(name);
    }
}
