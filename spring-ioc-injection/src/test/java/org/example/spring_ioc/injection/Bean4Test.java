package org.example.spring_ioc.injection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean4Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean4 bean4 = context.getBean("bean4", Bean4.class);
        System.out.println("bean4 = " + bean4);
        Bean4 bean4_1 = context.getBean("bean4_1", Bean4.class);
        System.out.println("bean4_1 = " + bean4_1);
    }
}
