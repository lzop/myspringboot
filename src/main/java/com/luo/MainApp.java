package com.luo;

import com.luo.event.ApplicationStartingEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
public class MainApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MainApp.class);
        springApplication.addListeners(new ApplicationStartingEventListener());
        springApplication.run(args);
//        SpringApplication.run(MainApp.class);
        log.info("my spring boot start !!!");
    }
}

