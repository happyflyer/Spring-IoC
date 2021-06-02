package org.example.spring_ioc.prepare.human;

import org.example.spring_ioc.prepare.car.Car;

/**
 * @author lifei
 */
public class LiSi extends HumanWithCar {
    public LiSi(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        car.start();
        car.stop();
    }
}
