package fr.pmuchallenger.ms_courses.converters;

import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;
import fr.pmuchallenger.ms_courses.model.entities.Partant;
import org.springframework.stereotype.Service;

/*
 * @author: pmu-manager
 */
@Service
public class PartantConverter {

    public PartantDto convertToDto(Partant partant) {
        return partant != null ? PartantDto.builder()
                .nom(partant.getNom())
                .numero(partant.getNumero())
                .build() : null;
    }

    public Partant convertToEntity(PartantAddRequest partantDto) {
        return partantDto != null ? Partant.builder()
                .numero(partantDto.getNumero())
                .nom(partantDto.getNom())
                .build() : null;
    }
}
