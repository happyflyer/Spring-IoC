package org.example.spring_ioc.annotation.scope;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lifei
 */
@Component("testBean3")
@Scope("singleton")
public abstract class TestBean3 {
    /**
     * createAnotherBean
     *
     * @return AnotherBean
     */
    @Lookup
    protected abstract AnotherBean createAnotherBean();

    public void printAnotherBean() {
        System.out.println("anotherBean = " + createAnotherBean());
    }
}
