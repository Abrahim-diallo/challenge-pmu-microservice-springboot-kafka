package fr.pmuchallenger.ms_courses.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author: PMU-MANAGER
 */
@Service
@Log4j2
public class KafkaCouresManagerConsumerService {

    @KafkaListener(topics = "courseCreated", groupId = "course-group")
    public void listenCourseManager(String message) {
        log.info("Received Message in group 'course-group': {}", message);
    }
}

