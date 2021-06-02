package org.example.spring_ioc.scope;

/**
 * @author lifei
 */
public class Bean5 {
    private AnotherBean anotherBean;

    public AnotherBean getAnotherBean() {
        return anotherBean;
    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    @Override
    public String toString() {
        return "Bean5{" +
                "anotherBean=" + anotherBean +
                '}';
    }
}
