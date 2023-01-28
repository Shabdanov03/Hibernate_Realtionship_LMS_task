package java8.service;

import java8.entity.Course;
import java8.entity.Instructor;
import java8.repository.InstructorsRepository;
import java8.repository.InstructorsRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class InstructorServiceImpl implements InstructorService{
InstructorsRepository instructorsRepository = new InstructorsRepositoryImpl();

    @Override
    public String saveInstructor(Instructor instructor) {
        return instructorsRepository.saveInstructor(instructor);
    }

    @Override
    public String updateInstructor(Long id, Instructor instructor) {
        return instructorsRepository.updateInstructor(id,instructor);
    }

    @Override
    public Optional<Instructor> getInstructorById(Long instructorId) {
        return instructorsRepository.getInstructorById(instructorId);
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        return instructorsRepository.getInstructorsByCourseId(courseId);
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        return instructorsRepository.deleteInstructorById(instructorId);
    }

    @Override
    public String assignInstructorToCourse(Long instructorId, Long courseId) {
        return instructorsRepository.assignInstructorToCourse(instructorId,courseId);
    }
}
