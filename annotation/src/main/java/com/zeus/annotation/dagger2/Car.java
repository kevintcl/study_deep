package com.zeus.annotation.dagger2;

import javax.inject.Inject;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-29.
 * =======================================
 */
public class Car {

    @Inject
    Engine engine;

    public Car() {
        DaggerCarComponent.builder().build().inject(this);
    }

    public Engine getEngine() {
        return this.engine;
    }

    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getEngine());
    }

}
