package com.example.pattern.strategy_pattern;

import com.example.pattern.strategy_pattern.behavior.FlyBehavior;

public class NormalFlyableDuck extends Duck {

    public NormalFlyableDuck(FlyBehavior flyBehavior) {
        super(flyBehavior);
    }

    @Override
    public void display() {
        System.out.println("display normal ducks");
    }
}
