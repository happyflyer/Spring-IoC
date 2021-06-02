package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.scope.AnotherBean;
import org.example.spring_ioc.annotation.scope.TestBean;
import org.example.spring_ioc.annotation.scope.TestBean3;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Annotation4Test {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration4.class);
        for (int i = 0; i < 10; i++) {
            TestBean testBean1 = context.getBean("testBean1", TestBean.class);
            System.out.println("testBean1 = " + testBean1);
        }
    }

    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration4.class);
        for (int i = 0; i < 10; i++) {
            TestBean testBean2 = context.getBean("testBean2", TestBean.class);
            System.out.println("testBean2 = " + testBean2);
        }
    }

    @Test
    public void test3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration4.class);
        for (int i = 0; i < 10; i++) {
            AnotherBean anotherBean = context.getBean("anotherBean", AnotherBean.class);
            System.out.println("anotherBean = " + anotherBean);
        }
    }

    @Test
    public void test4() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration4.class);
        TestBean3 testBean3 = context.getBean("testBean3", TestBean3.class);
        System.out.println("testBean3 = " + testBean3);
        for (int i = 0; i < 10; i++) {
            testBean3.printAnotherBean();
        }
    }
}
