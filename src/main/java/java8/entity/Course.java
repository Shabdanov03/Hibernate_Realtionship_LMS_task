package java8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@ToString(exclude = {"instructors", "lessons"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_id_generation")
    @SequenceGenerator(
            name = "courses_id_generation",
            sequenceName = "courses_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private int duration;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "image_link")
    private String imageLink;

    private String description;
    @ManyToMany(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "course", cascade =
            CascadeType.ALL)
    private List<Lesson> lessons;

    public Course(String courseName, int duration, LocalDate createAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createAt = createAt;
        this.imageLink = imageLink;
        this.description = description;
    }

}
