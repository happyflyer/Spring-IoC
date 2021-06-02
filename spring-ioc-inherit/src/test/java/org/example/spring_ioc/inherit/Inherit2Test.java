package org.example.spring_ioc.inherit;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Inherit2Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring2.xml");
        Class3 class3 = context.getBean("class3", Class3.class);
        System.out.println("class3 = " + class3);
        Class4 class4 = context.getBean("class4", Class4.class);
        System.out.println("class4 = " + class4);
    }
}
