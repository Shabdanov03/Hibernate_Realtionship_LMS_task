package java8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@ToString(exclude = {"course", "tasks"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lessons_id_generation")
    @SequenceGenerator(
            name = "lessons_id_generation",
            sequenceName = "lessons_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "link_video")
    private String linkVideo;
    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = {ALL})
    private Course course;
    @OneToMany(cascade = {REFRESH,DETACH,MERGE,PERSIST,REMOVE},fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Lesson(String name, String linkVideo) {
        this.name = name;
        this.linkVideo = linkVideo;
    }

}
