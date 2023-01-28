package java8.service;

import java8.entity.Course;
import java8.entity.Lesson;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface LessonService {
    String saveLesson(Lesson lesson, Long courseId);//(Lesson сакталып жатканда кандайдыр бир курска сакталуусу керек)
    String updateLesson(Long id,Lesson lesson);
    Optional<Lesson> getLessonById(Long lessonId);
    List<Lesson> getLessonsByCourseId(Long courseId); //(курска тиешелуу сабактарды чыгаруу);
}
