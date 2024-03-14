package fr.pmuchallenger.ms_courses.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
 * @author: pmu-manager
 */
@Service
@Log4j2
public class KafkaCoursesManagerProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCourseCreated(String message) {
        try {
            kafkaTemplate.send("courseCreated", message);
            log.info("Message sent to the topic courseCreated: {}", message);
        } catch (Exception e) {
            log.error("Error sending the message to the topic courseCreated: {}", e.getMessage());
        }
    }

    public void sendPartantUpdated(String message, String partantUpdated) {
        try {
            kafkaTemplate.send("partantsCreated", message);
            log.info("Message sent to the topic partantsCreated: {}", message);
        } catch (Exception e) {
            log.error("Error sending the message to the topic partantsCreated: {}", e.getMessage());
        }
    }

    public void sendPartantDeleted(String partantDeletedTopic, String paartantDeleted) {
        try {
            kafkaTemplate.send("partantsCreated", partantDeletedTopic);
            log.info("Message sent to the topic partantsCreated: {}", partantDeletedTopic);
        } catch (Exception e) {
            log.error("Error sending the message to the topic partantsCreated: {}", e.getMessage());
        }
    }
}
