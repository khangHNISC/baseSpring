package com.example.pattern.strategy_pattern.behavior;

public class NoFly implements FlyBehavior {
    @Override
    public String fly() {
        return "I cant fly";
    }
}
