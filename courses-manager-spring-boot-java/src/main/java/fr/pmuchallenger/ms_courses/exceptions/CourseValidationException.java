package fr.pmuchallenger.ms_courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseValidationException extends RuntimeException {
    public CourseValidationException(String message) {
        super(message);
    }
}
