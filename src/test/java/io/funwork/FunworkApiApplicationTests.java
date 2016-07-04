package io.funwork;

import io.funwork.api.FunworkApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FunworkApiApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(FunworkApiApplication.class, args);
    }

}
