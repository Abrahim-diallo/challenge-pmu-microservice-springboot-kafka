package fr.pmuchallenger.ms_courses.services.Impl;

import fr.pmuchallenger.ms_courses.converters.CourseConverter;
import fr.pmuchallenger.ms_courses.converters.PartantConverter;
import fr.pmuchallenger.ms_courses.kafka.KafkaCoursesManagerProducerService;
import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;
import fr.pmuchallenger.ms_courses.repository.CourseRepository;
import fr.pmuchallenger.ms_courses.repository.PartantRepository;
import fr.pmuchallenger.ms_courses.services.PartantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author: pmu-manager
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class PartantServiceImpl implements PartantService {
    private final CourseRepository courseRepository;
    private final PartantRepository partantRepository;
    private final PartantConverter partantConverter;
    private final CourseConverter courseConverter;
    private final KafkaCoursesManagerProducerService kafkaCoursesManagerProducerService;

    /**
     * TODO: Implement
     * @param partantId
     * @param partantAddRequest
     * @return
     */
    @Override
    public PartantDto updatePartant(Long partantId, PartantAddRequest partantAddRequest) {
        return null;
    }
    /**
     * TODO: Implement
     * @param partantId
     * @return
     */
    @Override
    public PartantDto getPartantById(Long partantId) {
        return null;
    }
    /**
     * TODO: Implement
     * @param courseId
     * @return
     */
    @Override
    public List<PartantDto> getAllPartantsForCourse(Long courseId) {
        return null;
    }
    /**
     * TODO: Implement
     * @param partantId
     */
    @Override
    public void deletePartant(Long partantId) {

    }
}
