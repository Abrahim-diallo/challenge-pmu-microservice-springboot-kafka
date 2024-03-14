package fr.pmuchallenger.ms_courses.services;

import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;

import java.util.List;

/*
 * @author: pmu-manager
 */
public interface PartantService {
    PartantDto updatePartant(Long partantId, PartantAddRequest partantAddRequest);

    PartantDto getPartantById(Long partantId);

    List<PartantDto> getAllPartantsForCourse(Long courseId);

    void deletePartant(Long partantId);

}
