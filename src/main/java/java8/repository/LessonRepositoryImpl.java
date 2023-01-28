package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Course;
import java8.entity.Lesson;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class LessonRepositoryImpl implements LessonRepository, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveLesson(Lesson lesson, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            lesson.setCourse(course);
            entityManager.persist(lesson);
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson.getName() + " successfully saved in " + course.getCourseName() + " course..!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateLesson(Long id, Lesson lesson) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson oldLesson = entityManager.find(Lesson.class, id);
            oldLesson.setName(lesson.getName());
            oldLesson.setLinkVideo(lesson.getLinkVideo());
            oldLesson.setTasks(lesson.getTasks());
            oldLesson.setCourse(oldLesson.getCourse());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated.....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Lesson> getLessonById(Long lessonId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.
                    createQuery("select l from Lesson  l where l.id = :lessonId", Lesson.class)
                            .setParameter("lessonId",lessonId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(lesson);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        try {
            List<Lesson> lessonList = new ArrayList<>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            lessonList.addAll(course.getLessons());
            entityManager.getTransaction().commit();
            entityManager.close();
            return lessonList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
