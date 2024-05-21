package org.sparta.scheduleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication // @ComponentScan 어노테이션을 포함함
// @ComponentScan => @Component 어노테이션이 붙은 클래스를 Bean으로 등록하라고 지시!
public class ScheduleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectApplication.class, args);
    }

}
