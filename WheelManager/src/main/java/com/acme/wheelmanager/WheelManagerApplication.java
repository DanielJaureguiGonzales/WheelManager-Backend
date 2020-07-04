package com.acme.wheelmanager;

import com.acme.wheelmanager.controller.RecommendationController;
import com.acme.wheelmanager.repository.RecommendationRepository;
import com.acme.wheelmanager.service.RecommendationService;
import com.acme.wheelmanager.service.RecommendationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public abstract class WheelManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WheelManagerApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
