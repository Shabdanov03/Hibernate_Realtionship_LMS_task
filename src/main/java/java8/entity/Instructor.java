package java8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@ToString(exclude = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructors_id_generation")
    @SequenceGenerator(
            name = "instructors_id_generation",
            sequenceName = "instructors_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToMany(mappedBy = "instructors",cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Course> courses;

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
