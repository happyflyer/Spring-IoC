package org.example.spring_ioc.lifecycle;

/**
 * @author lifei
 */
public class Bean9 {
    public void onInit() {
        System.out.println("Bean9.onInit");
    }

    public void onDestroy() {
        System.out.println("Bean9.onDestroy");
    }
}
