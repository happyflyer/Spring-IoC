package org.example.spring_ioc.lifecycle;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean10Test {
    @Test
    public void test() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
        Bean10 bean10 = context.getBean("bean10", Bean10.class);
        System.out.println("bean10 = " + bean10);
        context.close();
    }
}
