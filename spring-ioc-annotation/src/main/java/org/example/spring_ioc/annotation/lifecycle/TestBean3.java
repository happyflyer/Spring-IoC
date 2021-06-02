package org.example.spring_ioc.annotation.lifecycle;

/**
 * @author lifei
 */
public class TestBean3 {
    public void onInit() {
        System.out.println("TestBean3.onInit");
    }

    public void onDestroy() {
        System.out.println("TestBean3.onDestroy");
    }
}
