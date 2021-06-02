package org.example.spring_ioc.scope;

/**
 * @author lifei
 */
public abstract class Bean6 {
    /**
     * createAnotherBean
     *
     * @return AnotherBean
     */
    protected abstract AnotherBean createAnotherBean();

    public void printAnotherBean() {
        System.out.println("anotherBean = " + createAnotherBean());
    }
}
