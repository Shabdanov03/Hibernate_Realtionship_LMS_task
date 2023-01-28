package java8.service;

import java8.entity.Task;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
public interface TaskService {
    String saveTask(Task task,Long lessonId);
    String updateTask(Long id,Task task);
    List<Task> getAllTaskByLessonId(Long taskId);
    String deleteTaskById(Long taskId);
}
