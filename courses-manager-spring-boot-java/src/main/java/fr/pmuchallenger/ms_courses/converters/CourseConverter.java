package fr.pmuchallenger.ms_courses.converters;

import fr.pmuchallenger.ms_courses.model.dto.response.CourseDto;
import fr.pmuchallenger.ms_courses.model.entities.Course;
import org.springframework.stereotype.Service;

/*
 * @author: pmu-manager
 */
@Service
public class CourseConverter {

    public CourseDto convertToDto(Course course) {
        return CourseDto.builder()
                .date(course.getDate())
                .numero(course.getNumero())
                .nom(course.getNom())
                .build();
    }

    public static Course convertToEntity(CourseDto courseDto) {
        return Course.builder()
                .date(courseDto.getDate())
                .numero(courseDto.getNumero())
                .nom(courseDto.getNom())
                .build();
    }
}
