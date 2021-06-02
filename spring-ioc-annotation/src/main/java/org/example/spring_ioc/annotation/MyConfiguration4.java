package org.example.spring_ioc.annotation;

import org.example.spring_ioc.annotation.scope.MyScope;
import org.example.spring_ioc.annotation.scope.TestBean;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author lifei
 */
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.scope")
public class MyConfiguration4 {
    @Bean("testBean1")
    @Scope("singleton")
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean
    public static MyScope myScope() {
        return new MyScope();
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("myScope", myScope());
        return configurer;
    }
}
