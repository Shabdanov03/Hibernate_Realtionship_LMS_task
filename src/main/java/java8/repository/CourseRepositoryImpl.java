package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Course;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class CourseRepositoryImpl implements CourseRepository,AutoCloseable{
private final  EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();
    @Override
    public String saveCourse(Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved.....";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.createQuery("select c from Course c where c.id= :courseId", Course.class).
                    setParameter("courseId",courseId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(course);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public List<Course> getAllCourse() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Course> course = entityManager.createQuery("select c from Course c order by c.createAt").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return course;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public String updateCourse(Long id,Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course oldCourse = entityManager.find(Course.class, id);
            oldCourse.setCourseName(course.getCourseName());
            oldCourse.setDuration(course.getDuration());
            oldCourse.setCreateAt(course.getCreateAt());
            oldCourse.setImageLink(course.getImageLink());
            oldCourse.setDescription(course.getDescription());
            oldCourse.setInstructors(course.getInstructors());
            oldCourse.setLessons(course.getLessons());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated....!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public String deleteCourseById(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted...!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.createQuery("select l from Course l where l.courseName = :name", Course.class).
                    setParameter("name", name).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(course);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
