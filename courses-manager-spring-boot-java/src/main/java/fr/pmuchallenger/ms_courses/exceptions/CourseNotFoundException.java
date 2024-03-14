package fr.pmuchallenger.ms_courses.exceptions;

/*
 * @author: pmu-manager
 */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String format) {
        super("Course not found!");
    }
}