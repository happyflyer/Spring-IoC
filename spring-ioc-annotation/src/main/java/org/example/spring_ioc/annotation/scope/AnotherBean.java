package org.example.spring_ioc.annotation.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lifei
 */
@Component
@Scope("myScope")
public class AnotherBean {
}
