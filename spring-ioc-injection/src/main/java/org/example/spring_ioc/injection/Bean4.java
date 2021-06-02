package org.example.spring_ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author lifei
 */
public class Bean4 {
    private final String string;
    private final AnotherBean anotherBean;
    private String string1;
    private AnotherBean anotherBean1;
    private List<String> stringList;
    private List<AnotherBean> anotherBeanList;
    private Map<String, String> stringMap;
    private Map<AnotherBean, AnotherBean> anotherBeanMap;
    private Set<String> stringSet;
    private Set<AnotherBean> anotherBeanSet;
    private Properties properties;
    private AnotherBean anotherBean2;
    private AnotherBean anotherBean3;

    public Bean4(AnotherBean anotherBean, String string) {
        this.anotherBean = anotherBean;
        this.string = string;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public AnotherBean getAnotherBean1() {
        return anotherBean1;
    }

    public void setAnotherBean1(AnotherBean anotherBean1) {
        this.anotherBean1 = anotherBean1;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<AnotherBean> getAnotherBeanList() {
        return anotherBeanList;
    }

    public void setAnotherBeanList(List<AnotherBean> anotherBeanList) {
        this.anotherBeanList = anotherBeanList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<AnotherBean, AnotherBean> getAnotherBeanMap() {
        return anotherBeanMap;
    }

    public void setAnotherBeanMap(Map<AnotherBean, AnotherBean> anotherBeanMap) {
        this.anotherBeanMap = anotherBeanMap;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public Set<AnotherBean> getAnotherBeanSet() {
        return anotherBeanSet;
    }

    public void setAnotherBeanSet(Set<AnotherBean> anotherBeanSet) {
        this.anotherBeanSet = anotherBeanSet;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public AnotherBean getAnotherBean2() {
        return anotherBean2;
    }

    public void setAnotherBean2(AnotherBean anotherBean2) {
        this.anotherBean2 = anotherBean2;
    }

    public AnotherBean getAnotherBean3() {
        return anotherBean3;
    }

    public void setAnotherBean3(AnotherBean anotherBean3) {
        this.anotherBean3 = anotherBean3;
    }

    @Override
    public String toString() {
        return "Bean4{" +
                "\n\tanotherBean=" + anotherBean +
                ",\n\tstring='" + string + '\'' +
                ",\n\tanotherBean1=" + anotherBean1 +
                ",\n\tstring1='" + string1 + '\'' +
                ",\n\tstringList=" + stringList +
                ",\n\tanotherBeanList=" + anotherBeanList +
                ",\n\tstringMap=" + stringMap +
                ",\n\tanotherBeanMap=" + anotherBeanMap +
                ",\n\tstringSet=" + stringSet +
                ",\n\tanotherBeanSet=" + anotherBeanSet +
                ",\n\tproperties=" + properties +
                ",\n\tanotherBean2=" + anotherBean2 +
                ",\n\tanotherBean3=" + anotherBean3 +
                "\n}";
    }
}
