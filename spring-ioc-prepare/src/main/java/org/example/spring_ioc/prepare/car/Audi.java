package org.example.spring_ioc.prepare.car;

/**
 * @author lifei
 */
public class Audi implements Car {
    @Override
    public void start() {
        System.out.println("Audi start");
    }

    @Override
    public void stop() {
        System.out.println("Audi stop");
    }

    @Override
    public void turnLeft() {
        System.out.println("Audi turnLeft");
    }

    @Override
    public void turnRight() {
        System.out.println("Audi turnRight");
    }
}
