# [Spring-IoC](https://github.com/happyflyer/Spring-IoC)

- [Spring 框架小白的蜕变](https://www.imooc.com/learn/1108)

Java Web 发展史：

- 第一阶段：JavaBean + Servlet + JSP 逐步发展
- 第二阶段：面对 EJB 重量级框架带来的种种麻烦
- 第三阶段：SpringMVC / Struts + Spring + Hibernate / MyBatis
- 第四阶段：享受 SpringBoot “约定大于配置” 的种种乐趣
- 第五阶段：以 Dubbo 为代表的 SOA 微服务架构体系
- 第六阶段：SpringCloud 微服务架构技术生态圈

内容：

- 上篇
  - IoC 介绍
    - IoC 概念介绍
    - 实现一个自己的 IoC
- 中篇
  - SpringIoC 入门
  - Bean 的实例化和注入
    - 实例化 Bean 方式
    - 注入 Bean 方式
  - Bean 的作用域
    - singleton、prototype
    - web 相关作用域
    - 自定义作用域
  - Bean 的懒加载
  - Bean 初始化及销毁逻辑处理
  - Bean 属性继承
- 下篇
  - SpringIoC 注解
    - 注解的基本使用
    - 通过注解注入 Bean
    - 通过注解设定 Bean 的作用域
    - 通过注解开启 Bean 的懒加载
    - 通过注解编写 Bean 初始化及销毁逻辑处理

## 1. 实现一个自己的 IoC

```java
public class IocContainer {
    private final Map<String, Object> beans = new ConcurrentHashMap<>();
    // ...
}
```

```java
public Object getBean(String beanId) {
    return beans.get(beanId);
}
```

```java
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
```

```java
public class IocContainerTest {
    private final IocContainer iocContainer = new IocContainer();
    @Before
    public void before() {
        iocContainer.setBean(Audi.class, "audi");
        iocContainer.setBean(Buick.class, "buick");
        iocContainer.setBean(ZhangSan.class, "zhangsan", "audi");
        iocContainer.setBean(LiSi.class, "lisi", "buick");
    }
    @Test
    public void test() {
        Human zhangSan = (Human) iocContainer.getBean("zhangsan");
        zhangSan.goHome();
        Human liSi = (Human) iocContainer.getBean("lisi");
        liSi.goHome();
    }
}
```

## 2. 初始化 Bean

```xml
<dependencies>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
  </dependency>
</dependencies>
```

1. 通过默认构造方法实例化 Bean
2. 通过静态工厂方法实例化 Bean
3. 通过实例工厂方法实例化 Bean
4. 通过 Bean 的别名实例化 Bean

```xml
<!-- 1. 通过默认构造方法实例化Bean -->
<bean id="bean1" class="org.example.spring_ioc.instance.Bean1" name="bean1_1,bean1_2"/>
<!-- 2. 通过静态工厂方法实例化Bean -->
<bean id="bean2" class="org.example.spring_ioc.instance.Bean2Factory" factory-method="getBean2"/>
<!-- 3. 通过实例工厂方法实例化Bean -->
<bean id="bean3Factory" class="org.example.spring_ioc.instance.Bean3Factory"/>
<bean id="bean3" factory-bean="bean3Factory" factory-method="getBean3"/>
<!-- 4. 通过Bean的别名实例化Bean -->
<alias name="bean1" alias="bean1_3"/>
```

## 3. 注入 Bean

1. 通过构造方法注入 Bean
2. 通过 set 方法注入 Bean
3. 集合类 Bean 的型注入
4. `null` 值注入
5. 注入时创建内部 Bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean id="anotherBean" class="org.example.spring_ioc.injection.AnotherBean"/>
  <bean id="bean4" class="org.example.spring_ioc.injection.Bean4">
    <!-- 1. 通过构造方法注入Bean -->
    <constructor-arg index="0" name="anotherBean" ref="anotherBean"
                     type="org.example.spring_ioc.injection.AnotherBean"/>
    <constructor-arg index="1" name="string" value="aaaaaa" type="java.lang.String"/>
    <!-- 2. 通过set方法注入Bean -->
    <property name="string1" value="bbbbbb"/>
    <property name="anotherBean1" ref="anotherBean"/>
    <!-- 3. List类型注入 -->
    <property name="stringList">
      <list>
        <value>aaaaaa</value>
        <value>bbbbbb</value>
      </list>
    </property>
    <property name="anotherBeanList">
      <list>
        <ref bean="anotherBean"/>
        <ref bean="anotherBean"/>
      </list>
    </property>
    <!-- 3. Set类型注入 -->
    <property name="stringSet">
      <set>
        <value>aaaaaa</value>
        <value>bbbbbb</value>
      </set>
    </property>
    <property name="anotherBeanSet">
      <set>
        <ref bean="anotherBean"/>
        <ref bean="anotherBean"/>
      </set>
    </property>
    <!-- 3. Map类型注入 -->
    <property name="stringMap">
      <map>
        <entry key="cccccc" value="dddddd"/>
        <entry key="eeeeee" value="ffffff"/>
      </map>
    </property>
    <property name="anotherBeanMap">
      <map>
        <entry key-ref="anotherBean" value-ref="anotherBean"/>
      </map>
    </property>
    <!-- 3. Properties类型注入 -->
    <property name="properties">
      <props>
        <prop key="aaaaaa">bbbbbb</prop>
      </props>
    </property>
    <!-- 4. null值注入 -->
    <property name="anotherBean2">
      <null/>
    </property>
    <!-- 5. 注入时创建内部Bean -->
    <property name="anotherBean3">
      <bean class="org.example.spring_ioc.injection.AnotherBean"/>
    </property>
  </bean>
  <!-- 构造方法和set方法注入简写 -->
  <bean id="bean4_1" class="org.example.spring_ioc.injection.Bean4"
        c:anotherBean-ref="anotherBean" c:string="cccccc"
        p:anotherBean1-ref="anotherBean" p:string1="dddddd"/>
</beans>
```

## 4. Bean 作用域

- Singleton 作用域
- Prototype 作用域
- Web 环境作用域
  - request 作用域
  - session 作用域
  - application 作用域
  - websocket 作用域
- 自定义作用域
  - SimpleThreadScope 作用域

### 4.1. singleton 和 prototype

- `singleton` 单实例
- `prototype` 多实例

![singleton作用域](https://cdn.jsdelivr.net/gh/happyflyer/picture-bed@main/2021/singleton作用域.cqyfvshyoso.jpg)

![prototype作用域](https://cdn.jsdelivr.net/gh/happyflyer/picture-bed@main/2021/prototype作用域.58mon3kezds0.jpg)

![singleton和prototype作用域](https://cdn.jsdelivr.net/gh/happyflyer/picture-bed@main/2021/singleton和prototype作用域.665r643mzck0.jpg)

```xml
<!-- bean作用域 -->
<bean id="anotherBean" class="org.example.spring_ioc.scope.AnotherBean" scope="prototype"/>
<bean id="bean5" class="org.example.spring_ioc.scope.Bean5" scope="prototype">
  <property name="anotherBean" ref="anotherBean"/>
</bean>
```

### 4.2. 方法注入

```java
public abstract class Bean6 {
    protected abstract AnotherBean createAnotherBean();
    public void printAnotherBean() {
        System.out.println("anotherBean = " + createAnotherBean());
    }
}
```

```xml
<!-- 方法注入 -->
<bean id="anotherBean" class="org.example.spring_ioc.scope.AnotherBean" scope="prototype"/>
<bean id="bean6" class="org.example.spring_ioc.scope.Bean6">
  <lookup-method name="createAnotherBean" bean="anotherBean"/>
</bean>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Bean6 bean6 = context.getBean("bean6", Bean6.class);
System.out.println("bean6 = " + bean6);
for (int i = 0; i < 10; i++) {
    bean6.printAnotherBean();
}
```

### 4.3. web 相关作用域

- `request`：每个 `request` 请求都会创建一个单独的实例。
- `session`：每个 `session` 都会创建一个单独的实例。
- `application`：每个 `servletContext` 都会创建一个单独的实例。
- `websocket`：每个 `websocket` 链接都会创建一个单独的实例。

```xml
<dependencies>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>${spring.version}</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
  </dependency>
</dependencies>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>
  <servlet>
    <servlet-name>SpringServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring.xml</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>SpringServlet</servlet-name>
    <url-pattern>/*</url-pattern>
  </servlet-mapping>z
</web-app>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean class="org.example.spring_ioc.web.RequestController" scope="request"/>
  <bean class="org.example.spring_ioc.web.SessionController" scope="session"/>
  <bean class="org.example.spring_ioc.web.ApplicationController" scope="application"/>
</beans>
```

```java
@Controller
public class RequestController {
    @RequestMapping("testRequest")
    @ResponseBody
    public String test() {
        return this.toString();
    }
}
```

```java
@Controller
public class SessionController {
    @RequestMapping("testSession")
    @ResponseBody
    public String test() {
        return this.toString();
    }
}
```

```java
@Controller
public class ApplicationController {
    @RequestMapping("testApplication")
    @ResponseBody
    public String test() {
        return this.toString();
    }
}
```

### 4.4. 自定义作用域

- 自己实现一个双实例作用域

```java
public class MyScope implements Scope {
    private final Map<String, Object> map1 = new ConcurrentHashMap<>();
    private final Map<String, Object> map2 = new ConcurrentHashMap<>();
    // ...
}
```

```java
public Object get(String name, ObjectFactory<?> objectFactory) {
    if (!map1.containsKey(name)) {
        Object o = objectFactory.getObject();
        map1.put(name, o);
        return o;
    }
    if (!map2.containsKey(name)) {
        Object o = objectFactory.getObject();
        map2.put(name, o);
        return o;
    }
    int i = new Random().nextInt();
    if (i == 0) {
        return map1.get(name);
    } else {
        return map2.get(name);
    }
}
```

```java
public Object remove(String name) {
    if (map2.containsKey(name)) {
        Object o = map2.get(name);
        map2.remove(name);
        return o;
    }
    if (map1.containsKey(name)) {
        Object o = map1.get(name);
        map1.remove(name);
        return o;
    }
    return null;
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean id="myScope" class="org.example.spring_ioc.custom.MyScope"/>
  <bean id="simpleThreadScope" class="org.springframework.context.support.SimpleThreadScope"/>
  <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
    <property name="scopes">
      <map>
        <entry key="myScope" value-ref="myScope"/>
        <entry key="simpleThreadScope" value-ref="simpleThreadScope"/>
      </map>
    </property>
  </bean>
  <bean id="bean7" class="org.example.spring_ioc.custom.Bean7" scope="myScope"/>
</beans>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
for (int i = 0; i < 10; i++) {
    Bean7 bean7 = context.getBean("bean7", Bean7.class);
    System.out.println("bean7 = " + bean7);
}
```

### 4.5. SimpleThreadScope 作用域

```xml
<bean id="bean7" class="org.example.spring_ioc.custom.Bean7" scope="simpleThreadScope"/>
```

```java
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
for (int i = 0; i < 10; i++) {
    new Thread(() -> {
        Bean7 bean7 = context.getBean("bean7", Bean7.class);
        System.out.println("bean7 = " + bean7);
    }).start();
}
```

## 5. Bean 的懒加载

- Spring 容器会在创建容器时提前初始化 Singleton 作用域的 bean
- 但是如果 Bean 被标注了 `lazy-init="true"`，则该 Bean 只有在其被需要的时候才会被初始化
- 只对 singleton 作用域的 bean 有效

> 思考：为什么其他作用域的 Bean 不需要懒加载呢？

- 指定 `lazy-init`
- 指定 `default-lazy-init`

```xml
<bean id="bean8" class="org.example.spring_ioc.lazy.Bean8" lazy-init="true"/>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd"
       default-lazy-init="true">
  <bean id="bean8" class="org.example.spring_ioc.lazy.Bean8"/>
</beans>
```

- 适用场景：如果某个 Bean 在程序整个运行周期都可能不会被使用，那么可考虑设定该 Bean 为懒加载
- 优点：尽可能的节省了资源
- 缺点：可能会导致某个操作响应时间增加

## 6. Bean 的初始化和销毁

- 指定 `init-method` / `destroy-method`

```xml
<bean id="bean9" class="org.example.spring_ioc.lifecycle.Bean9"
        init-method="onInit" destroy-method="onDestroy"/>
```

- 指定 `default-init-method` / `default-destroy-method`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd"
       default-init-method="onInit" default-destroy-method="onDestroy">
  <bean id="bean9" class="org.example.spring_ioc.lifecycle.Bean9"/>
</beans>
```

- 实现 `InitializingBean` / `DisposableBean` 接口

```java
public class Bean10 implements InitializingBean, DisposableBean {
    public void destroy() throws Exception {
        System.out.println("Bean10.destroy");
    }
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean10.afterPropertiesSet");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean id="bean10" class="org.example.spring_ioc.lifecycle.Bean10"/>
</beans>
```

## 7. Bean 的属性继承

### 7.1. 方式一

```java
public class ParentClass {
    private String attribute1;
    private String attribute2;
    private String attribute3;
    // ...
}
```

```java
public class Class1 extends ParentClass {
    private String attribute4;
    private String attribute5;
    // ...
}
```

```java
public class Class2 extends ParentClass {
    private String attribute6;
    private String attribute7;
    // ...
}
```

```xml
<bean class="org.example.spring_ioc.inherit.ParentClass" abstract="true" id="parentClass">
  <property name="attribute1" value="value1"/>
  <property name="attribute2" value="value2"/>
  <property name="attribute3" value="value3"/>
</bean>
<bean class="org.example.spring_ioc.inherit.Class1" parent="parentClass" id="class1">
  <property name="attribute4" value="value4"/>
  <property name="attribute5" value="value5"/>
</bean>
<bean class="org.example.spring_ioc.inherit.Class2" parent="parentClass" id="class2">
  <property name="attribute6" value="value6"/>
  <property name="attribute7" value="value7"/>
</bean>
```

### 7.2. 方式二

```java
public class Class3 {
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    // ...
}
```

```java
public class Class4 {
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute6;
    private String attribute7;
    // ...
}
```

```xml
<bean abstract="true" id="parentClass2">
  <property name="attribute1" value="value1"/>
  <property name="attribute2" value="value2"/>
  <property name="attribute3" value="value3"/>
</bean>
<bean class="org.example.spring_ioc.inherit.Class3" parent="parentClass2" id="class3">
  <property name="attribute4" value="value4"/>
  <property name="attribute5" value="value5"/>
</bean>
<bean class="org.example.spring_ioc.inherit.Class4" parent="parentClass2" id="class4">
  <property name="attribute6" value="value6"/>
  <property name="attribute7" value="value7"/>
</bean>
```

## 8. 使用注解

### 8.1. 使用配置类

```java
@Configuration
public class MyConfiguration1 {
    @Bean(value = {"bean1", "bean1_1", "bean1_2", "bean1_3"})
    public Bean1 bean1() {
        return new Bean1();
    }
}
```

```java
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration1.class);
Bean1 bean1 = context.getBean("bean1", Bean1.class);
System.out.println("bean1 = " + bean1);
Bean1 bean1_1 = context.getBean("bean1_1", Bean1.class);
System.out.println("bean1_1 = " + bean1_1);
Bean1 bean1_2 = context.getBean("bean1_2", Bean1.class);
System.out.println("bean1_2 = " + bean1_2);
Bean1 bean1_3 = context.getBean("bean1_3", Bean1.class);
System.out.println("bean1_3 = " + bean1_3);
```

### 8.2. 包扫描

#### 8.2.1. 注解

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.beans")
public class MyConfiguration2 {
}
```

```java
@Component()
public class Bean2 {
}
```

```java
@Component(value = "newBean3")
public class Bean3 {
}
```

- `@Controller`：被标注在 Controller 层
- `@Service`：被标注在 Service 层
- `@Repository`：被标注在 DAO 层
- `@Component`：通用型注解

#### 8.2.2. XML 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
  <context:component-scan base-package="org.example.spring_ioc.annotation.beans"/>
</beans>
```

### 8.3. 注入 Bean

- 通过方法注入 Bean
  - 通过构造方法注入 Bean
  - 通过 set 方法注入 Bean
- 通过属性注入 Bean
- 集合类 Bean 的型注入
  - 直接注入集合实例
  - 将多个泛型的实例注入到集合
    - 将多个泛型的实例注入到 List
    - 控制泛型实例在 List 中的顺序
    - 将多个泛型实例注入到 Map
- String、Integer 等类型直接赋值
- SpringIoC 容器内置接口实例注入

#### 8.3.1. 构造方法注入

```java
@Component
public class MyBean {
    private final AnotherBean anotherBean1;
    @Autowired
    public MyBean(AnotherBean anotherBean1) {
        this.anotherBean1 = anotherBean1;
    }
}
```

#### 8.3.2. set 方法注入

```java
@Component
public class MyBean {
    private AnotherBean anotherBean2;
    @Autowired
    public void setAnotherBean2(AnotherBean anotherBean2) {
        this.anotherBean2 = anotherBean2;
    }
}
```

#### 8.3.3. 属性注入

```java
@Component
public class MyBean {
    @Autowired
    private AnotherBean anotherBean3;
}
```

#### 8.3.4. List 实例直接注入

```java
@Component
public class MyBean {
    private List<String> stringList;
    public List<String> getStringList() {
        return stringList;
    }
    @Autowired
    @Qualifier("stringList")
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
```

```java
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
}
```

#### 8.3.5. 多个泛型实例注入 List

```java
@Component
public class MyBean {
    private List<String> stringList;
    public List<String> getStringList() {
        return stringList;
    }
    @Autowired
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.injection")
public class MyConfiguration3 {
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
}
```

#### 8.3.6. Map 实例直接注入

```java
@Component
public class MyBean {
    private Map<String, Integer> integerMap;
    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }
    @Autowired
    @Qualifier("integerMap")
    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.injection")
public class MyConfiguration3 {
    @Bean("integerMap")
    public Map<String, Integer> integerMap() {
        Map<String, Integer> integerMap = new ConcurrentHashMap<>();
        integerMap.put("aaa", 111);
        integerMap.put("bbb", 222);
        return integerMap;
    }
}
```

#### 8.3.7. 多个泛型实例注入 Map

```java
@Component
public class MyBean {
    private Map<String, Integer> integerMap;
    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }
    @Autowired
    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.injection")
public class MyConfiguration3 {
    @Bean("int1")
    public Integer integer1() {
        return 333;
    }
    @Bean("int2")
    public Integer integer2() {
        return 444;
    }
}
```

#### 8.3.8. 直接注入

```java
@Component
public class MyBean {
    private String string;
    private Integer integer;
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
}
```

#### 8.3.9. SpringIoC 容器内置接口实例注入

```java
@Component
public class MyBean {
    private ApplicationContext context;
    public ApplicationContext getContext() {
        return context;
    }
    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
```

可直接将 `ApplicationContext` 注入进来，也可以注入以下接口及其实现类

- `BeanFactory`
- `Environment`
- `ResourceLoader`
- `ApplicationEventPublisher`
- `MessageSource`

### 8.4. Bean 作用域

#### 8.4.1. singleton 和 prototype

```java
@Component("testBean2")
@Scope("prototype")
public class TestBean {
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.scope")
public class MyConfiguration4 {
    @Bean("testBean1")
    @Scope("singleton")
    public TestBean testBean() {
        return new TestBean();
    }
}
```

#### 8.4.2. 自定义作用域

```java
public class MyScope implements Scope {
    private final Map<String, Object> map1 = new ConcurrentHashMap<>();
    private final Map<String, Object> map2 = new ConcurrentHashMap<>();
    // ...
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.scope")
public class MyConfiguration4 {
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
```

```java
@Component
@Scope("myScope")
public class AnotherBean {
}
```

#### 8.4.3. 方法注入

```java
@Component("testBean3")
@Scope("singleton")
public abstract class TestBean3 {
    @Lookup
    protected abstract AnotherBean createAnotherBean();
    public void printAnotherBean() {
        System.out.println("anotherBean = " + createAnotherBean());
    }
}
```

### 8.5. Bean 的懒加载

```java
@Component("testBean1")
@Lazy
public class TestBean {
    public TestBean() {
        System.out.println("TestBean已经被创建");
    }
}
```

```java
@Configuration
@ComponentScan(value = "org.example.spring_ioc.annotation.lazy")
public class MyConfiguration5 {
    @Bean("testBean2")
    @Lazy
    public TestBean testBean() {
        return new TestBean();
    }
}
```

```java
@Configuration
@Lazy
@ComponentScan(value = "org.example.spring_ioc.annotation.lazy")
public class MyConfiguration5 {
    // ...
}
```

### 8.6. Bean 的初始化和销毁

#### 8.6.1. 实现 InitializingBean 和 DisposableBean 接口

```java
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
@Component
@Lazy
public class TestBean1 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean1.destroy");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean1.afterPropertiesSet");
    }
}
```

#### 8.6.2. 注解 PostConstruct 和 PreDestroy

```xml
<dependency>
  <groupId>javax</groupId>
  <artifactId>javaee-api</artifactId>
  <version>7.0</version>
  <scope>provided</scope>
</dependency>
```

```java
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
@Lazy
public class TestBean2 {
    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean2.postConstruct");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("TestBean2.preDestroy");
    }
}
```

#### 8.6.3. 指定 initMethod 和 destroyMethod

```java
public class TestBean3 {
    public void onInit() {
        System.out.println("TestBean3.onInit");
    }
    public void onDestroy() {
        System.out.println("TestBean3.onDestroy");
    }
}
```

```java
@Configuration
@Lazy
@ComponentScan(value = "org.example.spring_ioc.annotation.lifecycle")
public class MyConfiguration6 {
    @Bean(initMethod = "onInit", destroyMethod = "onDestroy")
    public TestBean3 testBean3() {
        return new TestBean3();
    }
}
```

#### 8.6.4. 初始化和销毁顺序

```java
public class TestBean4 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean4.destroy");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean4.afterPropertiesSet");
    }
    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean4.postConstruct");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("TestBean4.preDestroy");
    }
    public void onInit() {
        System.out.println("TestBean4.onInit");
    }
    public void onDestroy() {
        System.out.println("TestBean4.onDestroy");
    }
}
```

```java
@Configuration
@Lazy
@ComponentScan(value = "org.example.spring_ioc.annotation.lifecycle")
public class MyConfiguration6 {
    @Bean(initMethod = "onInit", destroyMethod = "onDestroy")
    public TestBean4 testBean4() {
        return new TestBean4();
    }
}
```

```java
TestBean4.postConstruct
TestBean4.afterPropertiesSet
TestBean4.onInit
testBean4 = org.example.spring_ioc.annotation.lifecycle.TestBean4@3b6d844d
TestBean4.preDestroy
TestBean4.destroy
TestBean4.onDestroy
```
