package org.example.spring_ioc.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean6Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean6 bean6 = context.getBean("bean6", Bean6.class);
        System.out.println("bean6 = " + bean6);
        for (int i = 0; i < 10; i++) {
            bean6.printAnotherBean();
        }
    }
}
