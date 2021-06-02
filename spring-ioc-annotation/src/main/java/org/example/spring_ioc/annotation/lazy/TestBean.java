package org.example.spring_ioc.annotation.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author lifei
 */
@Component("testBean1")
@Lazy
public class TestBean {
    public TestBean() {
        System.out.println("TestBean已经被创建");
    }
}
