package mvc.app.todoapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @Size(min = 5, message = "Enter at least 5 characters")
    private String description;
    @Column(name = "target_date")
    private LocalDate targetDate;
    @Column(name = "is_done")
    private boolean done;
}
