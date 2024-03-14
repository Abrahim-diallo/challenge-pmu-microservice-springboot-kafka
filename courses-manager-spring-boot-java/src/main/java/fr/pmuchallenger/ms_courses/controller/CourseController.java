package fr.pmuchallenger.ms_courses.controller;

import fr.pmuchallenger.ms_courses.model.dto.request.CourseAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.CourseDto;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;
import fr.pmuchallenger.ms_courses.services.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * @author: pmu-manager
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Récupérer tous les partants pour un cours par ID")
    @GetMapping("/{courseId}/partants")
    public ResponseEntity<List<CourseDto>> getAllPartantsForCourseById(@PathVariable @Parameter(description = "ID du cours") Long courseId) {
        List<CourseDto> courseDtos = courseService.getAllPartantsForCourseById(courseId);
        return ResponseEntity.ok().body(courseDtos);
    }

    @Operation(summary = "Créer un nouveau cours")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cours créé avec succès"), @ApiResponse(responseCode = "400", description = "Requête invalide")})
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseAddRequest courseAddRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CourseDto courseResponseDtoCR = courseService.createCourse(courseAddRequest);
        return new ResponseEntity<>(courseResponseDtoCR, HttpStatus.CREATED);
    }

    @Operation(summary = "Mettre à jour un cours par ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable @Parameter(description = "ID du cours") Long id, @Valid @RequestBody CourseAddRequest courseAddRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CourseDto updatedCourseDtoUC = courseService.updateCourse(id, courseAddRequest);
        return new ResponseEntity<>(updatedCourseDtoUC, HttpStatus.OK);
    }

    @Operation(summary = "Supprimer un cours par ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable @Parameter(description = "ID du cours") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
    }

    @Operation(summary = "Lister tous les cours")
    @GetMapping
    public ResponseEntity<List<CourseDto>> listAllCourses() {
        List<CourseDto> coursesDtoLC = courseService.getAllCourses();
        return new ResponseEntity<>(coursesDtoLC, HttpStatus.OK);
    }

    @Operation(summary = "Ajouter un partant à un cours par ID")
    @PostMapping("/{courseId}/partants")
    public ResponseEntity<?> ajouterPartantToCourse(@PathVariable @Parameter(description = "ID du cours") Long courseId, @Valid @RequestBody PartantAddRequest partantAddRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        PartantDto newPartant = courseService.ajouterPartantToCourse(courseId, partantAddRequest);
        if (newPartant != null) {
            return new ResponseEntity<>(newPartant, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add partant to course.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/partants")
    public ResponseEntity<?> assignNumeroToPartants(@PathVariable Long id, @Valid @RequestBody PartantAddRequest partantAddRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        courseService.assignNumeroToPartants(courseService.getCourseById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
