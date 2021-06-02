package org.example.spring_ioc.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lifei
 */
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.injection")
public class MyConfiguration3 {
    @Bean("stringList")
    public List<String> stringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("111");
        stringList.add("222");
        return stringList;
    }

    @Bean
    @Order(56)
    public String string1() {
        return "333";
    }

    @Bean
    @Order(28)
    public String string2() {
        return "444";
    }

    @Bean("integerMap")
    public Map<String, Integer> integerMap() {
        Map<String, Integer> integerMap = new ConcurrentHashMap<>();
        integerMap.put("aaa", 111);
        integerMap.put("bbb", 222);
        return integerMap;
    }

    @Bean("int1")
    public Integer integer1() {
        return 333;
    }

    @Bean("int2")
    public Integer integer2() {
        return 444;
    }
}
