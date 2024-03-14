package fr.pmuchallenger.ms_courses.model.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/*
 * @author: pmu-manager
 */
@Entity
@Table(name = "partants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partant_id", unique = true, nullable = false, updatable = false)
    private Long partantId;

    @NotBlank(message = "nom cannot be blank")
    private String nom;

    @NotNull(message = "numero cannot be null")
    private Integer numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

}
