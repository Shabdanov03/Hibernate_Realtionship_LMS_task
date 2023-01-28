package java8;

import java8.config.HibernateConfig;
import java8.entity.Course;
import java8.entity.Instructor;
import java8.entity.Lesson;
import java8.entity.Task;
import java8.service.*;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {

            HibernateConfig.getManagerFactory();
            CourseService courseService = new CourseServiceImpl();
            InstructorService instructorService = new InstructorServiceImpl();
            LessonService lessonService = new LessonServiceImpl();
            TaskService taskService = new TaskServiceImpl();
            Course course1 = new Course("Java", 9, LocalDate.of(2022, 10, 3), "Photo", "Programing");
            Course course2 = new Course("JavaScript", 9, LocalDate.of(2022, 10, 3), "Photo", "Programing");
            Instructor instructor1 = new Instructor("Aijamal", "Assangazieva", "aijamal@gmail.com", "+996123456789");
            Instructor instructor2 = new Instructor("Albina", "Nurustanova", "albina@gmail.com", "+996098765432");
            Lesson lesson1 = new Lesson("OOP", "life");
            Lesson lesson2 = new Lesson("HTML", "in LMS");
            Task task1 = new Task("Abstrackt", LocalDate.of(2023, 1, 26), "What is OOP ?");
            Task task2 = new Task("CSS", LocalDate.of(2023, 1, 26), "Teg");

            while (true) {
                System.out.println("" +
                        "\n======COURSE COMMANDS======" +
                        "\n1.SAVE COURSE :" +
                        "\n2.GET COURSE BY ID :" +
                        "\n3.GET ALL COURSE :" +
                        "\n4.UPDATE COURSE :" +
                        "\n5.DELETE COURSE BY ID :" +
                        "\n6.GET COURSE BY NAME :" +
                        "\n======INSTRUCTOR COMMANDS======" +
                        "\n7.SAVE INSTRUCTOR :" +
                        "\n8.UPDATE INSTRUCTOR :" +
                        "\n9.GET INSTRUCTOR BY ID :" +
                        "\n10.GET INSTRUCTOR BY COURSE ID :" +
                        "\n11.DELETE INSTRUCTOR BY ID :" +
                        "\n12.ASSIGN INSTRUCTOR TO COURSE :" +
                        "\n======LESSON COMMANDS======" +
                        "\n13.SAVE LESSON :" +
                        "\n14.UPDATE LESSON :" +
                        "\n15.GET LESSON BY ID :" +
                        "\n16.GET LESSON BY COURSE ID :" +
                        "\n======TASK COMMANDS======" +
                        "\n17.SAVE TASK :" +
                        "\n18.UPDATE TASK :" +
                        "\n19.GET ALL TASK BY LESSON ID :" +
                        "\n20.DELETE TASK BY ID :" +
                        "");
                System.out.println("ENTER BY COMMANDS :");
                int com = new Scanner(System.in).nextInt();
                switch (com) {
                    case 1 -> System.out.println(courseService.saveCourse(course1));
                    case 2 -> {
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(courseService.getCourseById(courseId));
                    }
                    case 3 -> System.out.println(courseService.getAllCourse());
                    case 4 -> {
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(courseService.updateCourse(courseId, course2));
                    }
                    case 5 -> {
                        System.out.println("Enter by course id:");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(courseService.deleteCourseById(courseId));
                    }
                    case 6 -> {
                        System.out.println("Enter by course name");
                        String courseName = new Scanner(System.in).nextLine();
                        System.out.println(courseService.getCourseByName(courseName));
                    }
                    case 7 -> System.out.println(instructorService.saveInstructor(instructor1));
                    case 8 -> {
                        System.out.println("Enter by instructor id :");
                        Long instructorId = new Scanner(System.in).nextLong();
                        System.out.println(instructorService.updateInstructor(instructorId, instructor2));
                    }
                    case 9 -> {
                        System.out.println("Enter by instructor id :");
                        Long instructorId = new Scanner(System.in).nextLong();
                        System.out.println(instructorService.getInstructorById(instructorId));
                    }
                    case 10 -> {
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(instructorService.getInstructorsByCourseId(courseId));
                    }
                    case 11 -> {
                        System.out.println("Enter by instructorId :");
                        Long instructorId = new Scanner(System.in).nextLong();
                        System.out.println(instructorService.deleteInstructorById(instructorId));
                    }
                    case 12 -> {
                        System.out.println("Enter by instructor id :");
                        Long instructorId = new Scanner(System.in).nextLong();
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(instructorService.assignInstructorToCourse(instructorId, courseId));
                    }
                    case 13 -> {
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(lessonService.saveLesson(lesson1, courseId));
                    }
                    case 14 -> {
                        System.out.println("Enter by lesson id :");
                        Long lessonId = new Scanner(System.in).nextLong();
                        System.out.println(lessonService.updateLesson(lessonId, lesson2));
                    }
                    case 15 -> {
                        System.out.println("Enter by lesson id :");
                        Long lessonId = new Scanner(System.in).nextLong();
                        System.out.println(lessonService.getLessonById(lessonId));
                    }
                    case 16 -> {
                        System.out.println("Enter by course id :");
                        Long courseId = new Scanner(System.in).nextLong();
                        System.out.println(lessonService.getLessonsByCourseId(courseId));
                    }
                    case 17 -> {
                        System.out.println("Enter by lesson id :");
                        Long lessonId = new Scanner(System.in).nextLong();
                        System.out.println(taskService.saveTask(task1,lessonId));
                    }
                    case 18 -> {
                        System.out.println("Enter by task id :");
                        Long taskId = new Scanner(System.in).nextLong();
                        System.out.println(taskService.updateTask(taskId, task2));
                    }
                    case 19 -> {
                        System.out.println("Enter by lesson id :");
                        Long lessonId = new Scanner(System.in).nextLong();
                        System.out.println(taskService.getAllTaskByLessonId(lessonId));
                    }
                    case 20 -> {
                        System.out.println("Enter by task id :");
                        Long taskId = new Scanner(System.in).nextLong();
                        System.out.println(taskService.deleteTaskById(taskId));
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
