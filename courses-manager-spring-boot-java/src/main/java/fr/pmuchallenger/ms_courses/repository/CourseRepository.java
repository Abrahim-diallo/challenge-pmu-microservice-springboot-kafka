package fr.pmuchallenger.ms_courses.repository;

import fr.pmuchallenger.ms_courses.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/*
 * @author: pmu-manager
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByDateAndNumero(LocalDate date, Integer numero);
}

