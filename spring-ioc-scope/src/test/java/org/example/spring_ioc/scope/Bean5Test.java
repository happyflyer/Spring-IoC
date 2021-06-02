package org.example.spring_ioc.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean5Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean5 bean5_1 = context.getBean("bean5", Bean5.class);
        System.out.println("bean5_1 = " + bean5_1);
        Bean5 bean5_2 = context.getBean("bean5", Bean5.class);
        System.out.println("bean5_2 = " + bean5_2);
        AnotherBean anotherBean = context.getBean("anotherBean", AnotherBean.class);
        System.out.println("anotherBean = " + anotherBean);
        System.out.println(bean5_1.getAnotherBean() == bean5_2.getAnotherBean());
        System.out.println(bean5_1 == bean5_2);
    }
}
