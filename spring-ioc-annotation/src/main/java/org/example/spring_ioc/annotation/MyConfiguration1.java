package org.example.spring_ioc.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lifei
 */
@Configuration
public class MyConfiguration1 {
    @Bean(value = {"bean1", "bean1_1", "bean1_2", "bean1_3"})
    public Bean1 bean1() {
        return new Bean1();
    }
}
