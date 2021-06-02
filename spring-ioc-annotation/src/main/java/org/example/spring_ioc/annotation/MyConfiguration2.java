package org.example.spring_ioc.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lifei
 */
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.beans")
public class MyConfiguration2 {
}
