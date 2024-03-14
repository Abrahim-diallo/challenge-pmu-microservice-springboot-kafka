package fr.pmuchallenger.ms_courses.model.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class PartantDto implements Serializable {

    private String nom;

    private Integer numero;

}
