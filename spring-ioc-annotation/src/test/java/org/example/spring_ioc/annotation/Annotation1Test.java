package org.example.spring_ioc.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Annotation1Test {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration1.class);
        Bean1 bean1 = context.getBean("bean1", Bean1.class);
        System.out.println("bean1 = " + bean1);
        Bean1 bean1_1 = context.getBean("bean1_1", Bean1.class);
        System.out.println("bean1_1 = " + bean1_1);
        Bean1 bean1_2 = context.getBean("bean1_2", Bean1.class);
        System.out.println("bean1_2 = " + bean1_2);
        Bean1 bean1_3 = context.getBean("bean1_3", Bean1.class);
        System.out.println("bean1_3 = " + bean1_3);
    }
}
