package java8.repository;

import java8.entity.Course;
import java8.entity.Instructor;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface InstructorsRepository {
    String saveInstructor(Instructor instructor);
    String updateInstructor(Long id,Instructor instructor);
    Optional<Instructor> getInstructorById(Long instructorId);
    List<Instructor> getInstructorsByCourseId(Long courseId);//(тиешелуу курстун инструкторлорун чыгарып беруу)
    String deleteInstructorById(Long instructorId);//(инструктор очкондо, инструкторго тиешелуу курс очпошу керек)
    String assignInstructorToCourse(Long instructorId,Long courseId);//(инструкторду курска кошуп коюу(назначить)).


}
