package fr.pmuchallenger.ms_courses.utlis;

import fr.pmuchallenger.ms_courses.exceptions.CourseValidationException;
import fr.pmuchallenger.ms_courses.model.dto.request.CourseAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: pum-manager
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class CourseValidator {

    private final CourseRepository courseRepository;

    public void validateCourse(CourseAddRequest courseAddRequest) {
        try {
            validateNotNullFields(courseAddRequest);
            validateUniqueCourse(courseAddRequest.getDate(), courseAddRequest.getNumero());
            validateMinimumPartants(courseAddRequest);
            validatePartantNumbers(courseAddRequest.getPartant());
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            log.error("Validation de la course a échoué: {}", errorMessage);
            throw new CourseValidationException("Erreur de validation de la course: " + errorMessage);
        }
    }

    private void validateNotNullFields(CourseAddRequest courseAddRequest) {
        if (courseAddRequest.getDate() == null || courseAddRequest.getNumero() == null || courseAddRequest.getNom() == null || courseAddRequest.getPartant() == null) {
            throw new CourseValidationException("Certains champs de la course sont null.");
        }
    }

    private void validateUniqueCourse(LocalDate date, Integer numero) {
        if (courseRepository.existsByDateAndNumero(date, numero)) {
            throw new CourseValidationException("Une course existe déjà avec cette date et ce numéro.");
        }
    }

    private void validateMinimumPartants(CourseAddRequest courseAddRequest) {
        if (courseAddRequest.getPartant().size() < 3) {
            throw new CourseValidationException("Une course doit avoir au minimum 3 partants.");
        }
    }

    private void validatePartantNumbers(List<PartantAddRequest> partants) {
        Set<Integer> partantNumbers = new HashSet<>();
        for (PartantAddRequest partant : partants) {
            int partantNumber = partant.getNumero();
            if (partantNumber <= 0 || !partantNumbers.add(partantNumber)) {
                throw new CourseValidationException("Les numéros des partants doivent être uniques et consécutifs à partir de 1.");
            }
        }
    }
}
