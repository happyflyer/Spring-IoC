package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.beans.Bean2;
import org.example.spring_ioc.annotation.beans.Bean3;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Annotation2Test {
    @Test
    public void testAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration2.class);
        Bean2 bean2 = context.getBean("bean2", Bean2.class);
        System.out.println("bean2 = " + bean2);
        Bean3 bean3 = context.getBean("newBean3", Bean3.class);
        System.out.println("bean3 = " + bean3);
    }

    @Test
    public void testXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean2 bean2 = context.getBean("bean2", Bean2.class);
        System.out.println("bean2 = " + bean2);
        Bean3 bean3 = context.getBean("newBean3", Bean3.class);
        System.out.println("bean3 = " + bean3);
    }
}
