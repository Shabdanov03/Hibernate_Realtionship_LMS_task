package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import java8.config.HibernateConfig;
import java8.entity.Course;
import java8.entity.Instructor;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class InstructorsRepositoryImpl implements InstructorsRepository,AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveInstructor(Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved....";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateInstructor(Long id, Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor oldInstructor = entityManager.find(Instructor.class, id);
            oldInstructor.setFirstName(instructor.getFirstName());
            oldInstructor.setLastName(instructor.getLastName());
            oldInstructor.setEmail(instructor.getEmail());
            oldInstructor.setPhoneNumber(instructor.getPhoneNumber());
            oldInstructor.setCourses(instructor.getCourses());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated....";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Instructor> getInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.
                    createQuery("select i from Instructor i where i.id = :instructorId", Instructor.class)
                            .setParameter("instructorId",instructorId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(instructor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        try {
            List<Instructor> instructorList = new ArrayList<>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            instructorList.addAll(course.getInstructors());
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructorList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            instructor.getCourses().stream().forEach(s->s.setInstructors(null));
            instructor.setCourses(null);
            entityManager.remove(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted.......";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignInstructorToCourse(Long instructorId, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            Course course = entityManager.find(Course.class, courseId);
            List<Instructor> instructorList = new ArrayList<>(Arrays.asList(instructor));
            List<Course> courseList = new ArrayList<>(Arrays.asList(course));
            course.setInstructors(instructorList);
            instructor.setCourses(courseList);
            entityManager.merge(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully assigned as a course......";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
