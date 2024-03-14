package fr.pmuchallenger.ms_courses.exceptions;


import org.springframework.http.HttpStatus;

/*
 * @author: pmu-manager
 */
public class PartantNotFoundException extends RuntimeException {
    public PartantNotFoundException(HttpStatus notFound, String format) {
        super("Course not found!");
    }
}