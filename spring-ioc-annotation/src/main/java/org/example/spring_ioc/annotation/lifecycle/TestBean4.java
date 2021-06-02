package org.example.spring_ioc.annotation.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lifei
 */
public class TestBean4 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean4.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean4.afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean4.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("TestBean4.preDestroy");
    }

    public void onInit() {
        System.out.println("TestBean4.onInit");
    }

    public void onDestroy() {
        System.out.println("TestBean4.onDestroy");
    }
}
