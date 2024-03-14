package fr.pmuchallenger.ms_courses.model.dto.request;

import lombok.Builder;
import lombok.Data;

/*
 * @author: pmu-manager
 */
@Data
@Builder
public class PartantAddRequest {

    private Long PartantId;

    private Integer numero;

    private String nom;
}

