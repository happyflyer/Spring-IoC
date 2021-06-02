package org.example.spring_ioc.prepare.human;

import org.example.spring_ioc.prepare.car.Car;

/**
 * @author lifei
 */
public abstract class HumanWithCar implements Human {
    protected Car car;

    public HumanWithCar(Car car) {
        this.car = car;
    }

    /**
     * goHome
     */
    @Override
    public abstract void goHome();
}
