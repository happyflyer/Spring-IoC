package org.example.spring_ioc.lazy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean8Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("context已经被创建");
        Bean8 bean8 = context.getBean("bean8", Bean8.class);
        System.out.println("bean8 = " + bean8);
    }
}
