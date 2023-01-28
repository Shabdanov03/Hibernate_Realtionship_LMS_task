package java8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Shabdanov Ilim
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_id_generation")
    @SequenceGenerator(
            name = "tasks_id_generation",
            sequenceName = "task_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate deadLine;
    private String task;

    public Task(String name, LocalDate deadLine, String task) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "\nid=" + id +
                ",\nname='" + name + '\'' +
                ",\ndeadLine=" + deadLine +
                ",\ntask='" + task + '\'' +
                '}';
    }
}
