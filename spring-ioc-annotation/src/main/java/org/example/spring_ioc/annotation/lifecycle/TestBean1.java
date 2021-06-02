package org.example.spring_ioc.annotation.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author lifei
 */
@Component
@Lazy
public class TestBean1 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean1.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean1.afterPropertiesSet");
    }
}
