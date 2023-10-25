package com.gangbean.josthisapi;

import com.gangbean.josthiscore.JosthisCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackageClasses = {
        JosthisApiApplication.class,
        JosthisCoreApplication.class
})
@EntityScan(basePackages = {"com.gangbean.josthiscore"})
public class JosthisApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JosthisApiApplication.class, args);
    }

}
