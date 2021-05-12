package com.example.pattern.strategy_pattern.behavior;

public class FlyWithWings implements FlyBehavior {

    @Override
    public String fly() {
        return "flyWithWings";
    }
}
