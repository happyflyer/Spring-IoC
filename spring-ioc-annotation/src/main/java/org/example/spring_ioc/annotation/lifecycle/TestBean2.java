package org.example.spring_ioc.annotation.lifecycle;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lifei
 */
@Component
@Lazy
public class TestBean2 {
    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean2.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("TestBean2.preDestroy");
    }
}
