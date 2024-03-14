package fr.pmuchallenger.ms_courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class MsCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCourseApplication.class, args);
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

}
