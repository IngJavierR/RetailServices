package mx.com.axity.services;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("mx.com.axity")
@EnableJpaRepositories("mx.com.axity.persistence")
@EntityScan("mx.com.axity.model")
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Application.class);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
