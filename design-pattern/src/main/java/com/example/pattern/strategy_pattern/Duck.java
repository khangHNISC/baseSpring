package com.example.pattern.strategy_pattern;

import com.example.pattern.strategy_pattern.behavior.FlyBehavior;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@SuppressWarnings("unused")
abstract class Duck {
    private FlyBehavior flyBehavior;

    public String performFly() {
        return flyBehavior.fly();
    }

    public abstract void display();
}
