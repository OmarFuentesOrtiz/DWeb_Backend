package com.neoadventura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NeoAdventuraApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeoAdventuraApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {};
    }

}
