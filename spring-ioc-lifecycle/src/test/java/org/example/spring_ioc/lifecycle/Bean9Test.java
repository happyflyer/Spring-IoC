package org.example.spring_ioc.lifecycle;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean9Test {
    @Test
    public void test() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean9 bean9 = context.getBean("bean9", Bean9.class);
        System.out.println("bean9 = " + bean9);
        context.close();
    }
}
