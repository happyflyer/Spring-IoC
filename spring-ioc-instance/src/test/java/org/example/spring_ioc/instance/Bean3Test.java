package org.example.spring_ioc.instance;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 3. 通过实例工厂方法实例化Bean
 */
public class Bean3Test {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean3Factory bean3Factory = context.getBean("bean3Factory", Bean3Factory.class);
        System.out.println("bean3 = " + bean3Factory.getBean3());
        Bean3 bean3 = context.getBean("bean3", Bean3.class);
        System.out.println("bean3 = " + bean3);
    }
}
