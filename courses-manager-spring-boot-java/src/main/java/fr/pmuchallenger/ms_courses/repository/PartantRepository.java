package fr.pmuchallenger.ms_courses.repository;

import fr.pmuchallenger.ms_courses.model.entities.Partant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * @author: pmu-manager
 */
public interface PartantRepository extends JpaRepository<Partant, Long> {
    List<Partant> findByCourse_CourseId(Long courseId);
}
