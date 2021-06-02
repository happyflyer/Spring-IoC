package org.example.spring_ioc.custom;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean7Test {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        for (int i = 0; i < 10; i++) {
            Bean7 bean7 = context.getBean("bean7", Bean7.class);
            System.out.println("bean7 = " + bean7);
        }
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Bean7 bean7 = context.getBean("bean7", Bean7.class);
                System.out.println("bean7 = " + bean7);
            }).start();
        }
    }
}
