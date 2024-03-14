package fr.pmuchallenger.ms_courses.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.pmuchallenger.ms_courses.constants.AppCouresManagerConstants;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
 * @author: pmu-manager
 */
@Entity
@Table(name = "courses")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", unique = true, nullable = false, updatable = false)
    private Long courseId;

    @JsonFormat(pattern = AppCouresManagerConstants.DATE_LOCAL_FORMAT, shape = JsonFormat.Shape.STRING)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Partant> partantList;


}
