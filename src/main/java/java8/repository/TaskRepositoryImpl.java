package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Lesson;
import java8.entity.Task;
import java8.service.TaskService;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Shabdanov Ilim
 **/
public class TaskRepositoryImpl implements TaskRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveTask(Task task,Long lessonId) {
        try {
            List<Task> taskList = new ArrayList<>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            taskList.addAll(lesson.getTasks());
            taskList.add(task);
            lesson.setTasks(taskList);
            entityManager.persist(task);
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task.getName() + "Successfully saved....";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public String updateTask(Long id, Task task) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task oldTAsk = entityManager.find(Task.class, id);
            oldTAsk.setName(task.getName());
            oldTAsk.setDeadLine(task.getDeadLine());
            oldTAsk.setTask(task.getTask());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated.....!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        try {
            List<Task> taskList = new ArrayList<>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            taskList.addAll(lesson.getTasks());
            entityManager.getTransaction().commit();
            entityManager.close();
            return taskList;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteTaskById(Long taskId) {
        try {
            EntityManager entityManager =entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, taskId);
            entityManager.remove(task);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted by id......";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Wrong input ....!";
    }

    @Override
    public void close() throws Exception {

    }
}
