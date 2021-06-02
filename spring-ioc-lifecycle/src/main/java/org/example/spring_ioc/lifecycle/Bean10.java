package org.example.spring_ioc.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author lifei
 */
public class Bean10 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean10.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean10.afterPropertiesSet");
    }
}
