package org.example.spring_ioc.annotation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lifei
 */
@Component
public class MyBean {
    private final AnotherBean anotherBean1;
    private AnotherBean anotherBean2;
    @Autowired
    private AnotherBean anotherBean3;

    private List<String> stringList;

    private Map<String, Integer> integerMap;

    private String string;
    private Integer integer;

    private ApplicationContext context;

    @Autowired
    public MyBean(AnotherBean anotherBean1) {
        this.anotherBean1 = anotherBean1;
    }

    @Autowired
    public void setAnotherBean2(AnotherBean anotherBean2) {
        this.anotherBean2 = anotherBean2;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Autowired
    @Qualifier("stringList")
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }

    @Autowired
    // @Qualifier("integerMap")
    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    public String getString() {
        return string;
    }

    @Value("555")
    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    @Value("666")
    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public ApplicationContext getContext() {
        return context;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "\n\tanotherBean1=" + anotherBean1 +
                ", \n\tanotherBean2=" + anotherBean2 +
                ", \n\tanotherBean3=" + anotherBean3 +
                ", \n\tstringList=" + stringList +
                ", \n\tintegerMap=" + integerMap +
                ", \n\tstring='" + string + '\'' +
                ", \n\tinteger=" + integer +
                ", \n\tcontext=" + context +
                "\n}";
    }
}
