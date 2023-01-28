package java8.service;

import java8.entity.Task;
import java8.repository.TaskRepository;
import java8.repository.TaskRepositoryImpl;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
public class TaskServiceImpl implements TaskService{
TaskRepository taskRepository = new TaskRepositoryImpl();
    @Override
    public String saveTask(Task task,Long lessonId) {
        return taskRepository.saveTask(task,lessonId);
    }

    @Override
    public String updateTask(Long id, Task task) {
        return taskRepository.updateTask(id,task);
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long taskId) {
        return taskRepository.getAllTaskByLessonId(taskId);
    }

    @Override
    public String deleteTaskById(Long taskId) {
        return taskRepository.deleteTaskById(taskId);
    }
}
