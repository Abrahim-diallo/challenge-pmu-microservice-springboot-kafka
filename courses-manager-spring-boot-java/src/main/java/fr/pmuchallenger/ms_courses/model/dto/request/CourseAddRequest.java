package fr.pmuchallenger.ms_courses.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.pmuchallenger.ms_courses.constants.AppCouresManagerConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/*
 * @author: pmu-manager
 */
@Data
@Builder
public class CourseAddRequest {
    @JsonFormat(pattern = AppCouresManagerConstants.DATE_LOCAL_FORMAT, shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDate date;

    @NotNull
    private Integer numero;

    @NotNull
    private String nom;

    private List<PartantAddRequest> partant;

}

