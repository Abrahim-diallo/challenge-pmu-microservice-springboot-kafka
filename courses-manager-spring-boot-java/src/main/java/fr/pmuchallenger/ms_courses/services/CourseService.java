package fr.pmuchallenger.ms_courses.services;

import fr.pmuchallenger.ms_courses.model.dto.request.CourseAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.request.PartantAddRequest;
import fr.pmuchallenger.ms_courses.model.dto.response.CourseDto;
import fr.pmuchallenger.ms_courses.model.dto.response.PartantDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: PMU Challenge
 */
public interface CourseService {
    List<CourseDto> getAllCourses();

    CourseDto getCourseById(Long courseId);

    CourseDto createCourse(CourseAddRequest courseAddRequest);


    CourseDto updateCourse(Long courseId, CourseAddRequest courseAddRequest);

    void deleteCourse(Long courseId);

    boolean checkCourseUniqueness(LocalDate date, Integer numero);

    List<CourseDto> getAllPartantsForCourseById(Long courseId);

    PartantDto ajouterPartantToCourse(Long courseId, PartantAddRequest partantAddRequest);

    void assignNumeroToPartants(CourseDto courseById);
}
