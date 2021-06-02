package org.example.spring_ioc.prepare;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IoC 容器
 * 实例化bean
 * 保存bean，每一个bean产生一个唯一id与之对应
 * 提供bean
 *
 * @author lifei
 */
public class IocContainer {
    private final Map<String, Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String beanId) {
        return beans.get(beanId);
    }

    public void setBean(Class<?> clazz, String beanId, String... paramBeanIds) {
        Object[] paramValues = new Object[paramBeanIds.length];
        for (int i = 0; i < paramBeanIds.length; i++) {
            paramValues[i] = beans.get(paramBeanIds[i]);
        }
        Object bean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                bean = constructor.newInstance(paramValues);
            } catch (InstantiationException | IllegalAccessException |
                    InvocationTargetException ignored) {
            }
        }
        if (bean == null) {
            throw new RuntimeException("找不到合适的构造方法去实例化bean");
        }
        beans.put(beanId, bean);
    }
}
