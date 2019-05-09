package com.zeus.annotation.dagger2;

import dagger.Component;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-29.
 * =======================================
 */
@Component
public interface CarComponent {
    void inject(Car car);
}
