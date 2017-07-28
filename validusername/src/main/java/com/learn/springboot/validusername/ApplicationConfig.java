package com.learn.springboot.validusername;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Main Class.
 * 
 * @author felipe
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class ApplicationConfig {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(ApplicationConfig.class, args);
    }
}
