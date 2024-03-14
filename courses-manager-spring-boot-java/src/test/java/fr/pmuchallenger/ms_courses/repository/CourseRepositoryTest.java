package fr.pmuchallenger.ms_courses.repository;

import fr.pmuchallenger.ms_courses.converters.CourseConverter;
import fr.pmuchallenger.ms_courses.converters.PartantConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Log4j2
@RequiredArgsConstructor
public class CourseRepositoryTest {
    private final CourseRepository courseRepository;
    private final PartantRepository partantRepository;
    private final PartantConverter partantConverter;
    private final CourseConverter courseConverter;

    //given

    //when

    //then

}
