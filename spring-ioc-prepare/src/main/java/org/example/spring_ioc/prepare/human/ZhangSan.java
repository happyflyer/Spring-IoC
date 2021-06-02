package org.example.spring_ioc.prepare.human;

import org.example.spring_ioc.prepare.car.Car;

/**
 * @author lifei
 */
public class ZhangSan extends HumanWithCar {
    public ZhangSan(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
