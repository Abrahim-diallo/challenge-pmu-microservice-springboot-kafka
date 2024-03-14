package fr.pmuchallenger.ms_courses.services.Impl;

import fr.pmuchallenger.ms_courses.converters.CourseConverter;
import fr.pmuchallenger.ms_courses.converters.PartantConverter;
import fr.pmuchallenger.ms_courses.exceptions.CourseNotFoundException;
import fr.pmuchallenger.ms_courses.exceptions.CourseValidationException;
import fr.pmuchallenger.ms_courses.exceptions.PartantNotFoundException;
import fr.pmuchallenger.ms_courses.kafka.KafkaCoursesManagerProducerService;
import fr.pmuchallenger.ms_courses.model.dto.request.CourseAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.CourseDto;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;
import fr.pmuchallenger.ms_courses.model.entities.Course;
import fr.pmuchallenger.ms_courses.model.entities.Partant;
import fr.pmuchallenger.ms_courses.repository.CourseRepository;
import fr.pmuchallenger.ms_courses.repository.PartantRepository;
import fr.pmuchallenger.ms_courses.services.CourseService;
import fr.pmuchallenger.ms_courses.utlis.CourseValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PartantRepository partantRepository;
    private final CourseConverter courseConverter;
    private final PartantConverter partantConverter;
    private final KafkaCoursesManagerProducerService kafkaCoursesManagerProducerService;
    private final CourseValidator courseValidator;

    @Override
    public List<CourseDto> getAllCourses() {
        log.info("Récupération de toutes les courses");
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            log.warn("Aucune course trouvée");
            throw new CourseNotFoundException("Aucune course trouvée");
        }
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = courseConverter.convertToDto(course);
            courseDto.setPartant(Collections.emptyList());
            courseDtos.add(courseDto);
        }
        log.info("Récupération de {} courses", courseDtos.size());
        return courseDtos;
    }

    public CourseDto getCourseById(Long courseId) {
        log.info("Récupération de la course avec l'identifiant : {}", courseId);
        return courseRepository.findById(courseId).map(courseConverter::convertToDto).orElseThrow(() -> {
            log.error("Aucune course trouvée avec l'identifiant : {}", courseId);
            throw new CourseNotFoundException("Aucune course trouvée avec l'identifiant : " + courseId);
        });
    }

    @Override
    public CourseDto createCourse(CourseAddRequest courseAddRequest) {
        log.info("Création d'une nouvelle course");
        try {
            courseValidator.validateCourse(courseAddRequest);

            List<Partant> partants = new ArrayList<>();
            if (courseAddRequest.getPartant() != null && !courseAddRequest.getPartant().isEmpty()) {
                for (PartantAddRequest partantAddRequest : courseAddRequest.getPartant()) {
                    Partant partant = partantConverter.convertToEntity(partantAddRequest);
                    partants.add(partant);
                }
            }
            CourseDto courseDto = CourseDto.builder().date(courseAddRequest.getDate()).numero(courseAddRequest.getNumero()).nom(courseAddRequest.getNom()).partant(partants.stream().map(partantConverter::convertToDto).collect(Collectors.toList())).build();

            Course course = courseConverter.convertToEntity(courseDto);
            course = courseRepository.save(course);
            kafkaCoursesManagerProducerService.sendCourseCreated("Course created: " + course);

            return courseConverter.convertToDto(course);
        } catch (CourseValidationException e) {
            log.error("Erreur lors de la création de la course: {}", e.getMessage());
            throw new CourseValidationException("Erreur lors de la création de la course: " + e.getMessage());
        }
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseAddRequest courseAddRequest) {
        log.info("Mise à jour du cours avec l'identifiant : {}", courseId);

        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Cours non trouvé avec l'identifiant : {}", courseId);
            throw new CourseNotFoundException("Cours non trouvé avec l'identifiant : " + courseId);
        });

        try {
            courseValidator.validateCourse(courseAddRequest);

            course.setDate(courseAddRequest.getDate());
            course.setNumero(courseAddRequest.getNumero());
            course.setNom(courseAddRequest.getNom());

            Course updatedCourse = courseRepository.save(course);
            log.info("Cours mis à jour avec succès : {}", updatedCourse);

            return courseConverter.convertToDto(updatedCourse);
        } catch (CourseValidationException e) {
            log.error("Erreur lors de la mise à jour de la course: {}", e.getMessage());
            throw new CourseValidationException("Erreur lors de la mise à jour de la course: " + e.getMessage());
        }
    }

    @Override
    public void deleteCourse(Long courseId) {
        log.info("Suppression du cours avec l'identifiant : {}", courseId);
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            log.info("Cours avec l'identifiant {} supprimé avec succès", courseId);
        } else {
            log.error("Cours non trouvé avec l'identifiant : {}", courseId);
            throw new CourseNotFoundException("Cours non trouvé avec l'identifiant : " + courseId);
        }
    }

    @Override
    public boolean checkCourseUniqueness(LocalDate date, Integer numero) {
        log.info("Vérification de l'unicité de la course pour la date: {} et le numéro: {}", date, numero);
        boolean isUnique = !courseRepository.existsByDateAndNumero(date, numero);
        if (isUnique) {
            log.info("La course avec la date {} et le numéro {} est unique", date, numero);
        } else {
            log.info("La course avec la date {} et le numéro {} existe déjà", date, numero);
        }
        return isUnique;
    }

    @Override
    public List<CourseDto> getAllPartantsForCourseById(Long courseId) {
        log.info("Récupération de tous les partants pour la course avec l'identifiant : {}", courseId);

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Aucun cours trouvé avec l'identifiant " + courseId));
        List<Partant> partants = partantRepository.findByCourse_CourseId(courseId);
        if (partants.isEmpty()) {
            log.warn("Aucun partant trouvé pour la course avec l'identifiant : {}", courseId);
            throw new PartantNotFoundException(HttpStatus.NOT_FOUND, String.format("Aucun partant trouvé pour la course avec l'identifiant %d", courseId));
        }
        CourseDto courseDto = courseConverter.convertToDto(course);
        courseDto.setPartant(partants.stream().map(partantConverter::convertToDto).collect(Collectors.toList()));

        log.info("Récupération de {} partants pour la course avec l'identifiant : {}", partants.size(), courseId);

        return Collections.singletonList(courseDto);
    }

    @Override
    public PartantDto ajouterPartantToCourse(Long courseId, PartantAddRequest partantAddRequest) {
        log.info("Ajout d'un nouveau partant au cours avec l'identifiant : {}", courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Cours non trouvé avec l'identifiant : {}", courseId);
            return new CourseNotFoundException("Cours non trouvé avec l'identifiant " + courseId);
        });
        Partant newPartant = Partant.builder().numero(partantAddRequest.getNumero()).nom(partantAddRequest.getNom()).course(course).build();
        Partant savedPartant = partantRepository.save(newPartant);
        log.info("Nouveau partant ajouté avec succès au cours avec l'identifiant : {}", courseId);
        return partantConverter.convertToDto(savedPartant);
    }

    /**
     * TODO :IMPLEMENTATION
     *
     * @param courseById
     */
    @Override
    public void assignNumeroToPartants(CourseDto courseById) {

    }
}
