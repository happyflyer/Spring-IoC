package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.lazy.TestBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Annotation5Test {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration5.class);
        System.out.println("context已经被创建");
        TestBean testBean1 = context.getBean("testBean1", TestBean.class);
        System.out.println("testBean1 = " + testBean1);
        TestBean testBean2 = context.getBean("testBean2", TestBean.class);
        System.out.println("testBean2 = " + testBean2);
    }
}
