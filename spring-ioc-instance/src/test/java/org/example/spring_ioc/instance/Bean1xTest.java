package org.example.spring_ioc.instance;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 4. 通过Bean的别名实例化Bean
 */
public class Bean1xTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean1 bean1_1 = context.getBean("bean1_1", Bean1.class);
        System.out.println("bean1_1 = " + bean1_1);
        Bean1 bean1_2 = context.getBean("bean1_2", Bean1.class);
        System.out.println("bean1_2 = " + bean1_2);
        Bean1 bean1_3 = context.getBean("bean1_3", Bean1.class);
        System.out.println("bean1_3 = " + bean1_3);
    }
}
