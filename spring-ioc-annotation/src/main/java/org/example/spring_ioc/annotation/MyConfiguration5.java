package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.lazy.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author lifei
 */
@Configuration
@Lazy
@ComponentScan(value = "org.example.spring_ioc.annotation.lazy")
public class MyConfiguration5 {
    @Bean("testBean2")
    @Lazy
    public TestBean testBean() {
        return new TestBean();
    }
}
