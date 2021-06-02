package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.injection.MyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Annotation3Test {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration3.class);
        MyBean myBean = context.getBean("myBean", MyBean.class);
        System.out.println("myBean = " + myBean);
    }
}
