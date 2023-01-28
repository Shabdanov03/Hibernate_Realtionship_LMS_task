package java8.repository;

import java8.entity.Lesson;
import java8.entity.Task;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
public interface TaskRepository {
    String saveTask(Task task,Long lessonId);
    String updateTask(Long id,Task task);
    List<Task> getAllTaskByLessonId(Long lessonId);
    String deleteTaskById(Long taskId);
}
