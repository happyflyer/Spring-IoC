package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.lifecycle.TestBean1;
import org.example.spring_ioc.annotation.lifecycle.TestBean2;
import org.example.spring_ioc.annotation.lifecycle.TestBean3;
import org.example.spring_ioc.annotation.lifecycle.TestBean4;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Annotation6Test {
    @Test
    public void test1() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration6.class);
        TestBean1 testBean1 = context.getBean("testBean1", TestBean1.class);
        System.out.println("testBean1 = " + testBean1);
        context.close();
    }

    @Test
    public void test2() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration6.class);
        TestBean2 testBean2 = context.getBean("testBean2", TestBean2.class);
        System.out.println("testBean2 = " + testBean2);
        context.close();
    }

    @Test
    public void test3() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration6.class);
        TestBean3 testBean3 = context.getBean("testBean3", TestBean3.class);
        System.out.println("testBean3 = " + testBean3);
        context.close();
    }

    @Test
    public void test4() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration6.class);
        TestBean4 testBean4 = context.getBean("testBean4", TestBean4.class);
        System.out.println("testBean4 = " + testBean4);
        context.close();
    }
}
