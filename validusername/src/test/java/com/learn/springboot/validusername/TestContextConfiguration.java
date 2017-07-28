package com.learn.springboot.validusername;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author felipe
 *
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.learn.springboot.validusername" })
@Configuration
public class TestContextConfiguration {
    //
}
