package fr.pmuchallenger.ms_courses.exceptions;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

/*
 * @author: pmu-manager
 */
@ControllerAdvice
@Log4j2
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CourseNotFoundException.class, PartantNotFoundException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleException(Exception exception) {
        log.debug("--- handleException");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(
                Map.of("message", exception.getMessage()),
                headers, HttpStatus.BAD_REQUEST);
    }
}
