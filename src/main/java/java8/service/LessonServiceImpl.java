package java8.service;

import java8.entity.Course;
import java8.entity.Lesson;
import java8.repository.LessonRepository;
import java8.repository.LessonRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class LessonServiceImpl implements LessonService{
LessonRepository lessonRepository = new LessonRepositoryImpl();
    @Override
    public String saveLesson(Lesson lesson, Long courseId) {
        return lessonRepository.saveLesson(lesson,courseId);
    }

    @Override
    public String updateLesson(Long id, Lesson lesson) {
        return lessonRepository.updateLesson(id,lesson);
    }

    @Override
    public Optional<Lesson> getLessonById(Long lessonId) {
        return lessonRepository.getLessonById(lessonId);
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.getLessonsByCourseId(courseId);
    }
}
