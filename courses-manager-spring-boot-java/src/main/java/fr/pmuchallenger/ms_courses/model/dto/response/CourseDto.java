package fr.pmuchallenger.ms_courses.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
 * @author: pmu-manager
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto implements Serializable {
    private LocalDate date;

    private Integer numero;

    private String nom;

    private List<PartantDto> partant;

}
