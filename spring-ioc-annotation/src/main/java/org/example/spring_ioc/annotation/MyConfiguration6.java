package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.lifecycle.TestBean3;
import org.example.spring_ioc.annotation.lifecycle.TestBean4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author lifei
 */
@Configuration
@Lazy
@ComponentScan(value = "org.example.spring_ioc.annotation.lifecycle")
public class MyConfiguration6 {
    @Bean(initMethod = "onInit", destroyMethod = "onDestroy")
    public TestBean3 testBean3() {
        return new TestBean3();
    }

    @Bean(initMethod = "onInit", destroyMethod = "onDestroy")
    public TestBean4 testBean4() {
        return new TestBean4();
    }
}
