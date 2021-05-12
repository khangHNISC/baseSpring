package com.example.pattern.strategy_pattern;

import com.example.pattern.strategy_pattern.behavior.FlyWithWings;
import com.example.pattern.strategy_pattern.behavior.NoFly;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalFlyableDuckTest {

    @Test
    void givenNormalDuck_ThenFlyWithWings() {
        Duck duck = new NormalFlyableDuck(new FlyWithWings());
        assertEquals("flyWithWings", duck.performFly());
    }

    @Test
    void givenNormalFlyableDuck_ThenRestNoFly() {
        Duck duck = new NormalFlyableDuck(new FlyWithWings());
        duck.setFlyBehavior(new NoFly());
        assertEquals("I cant fly", duck.performFly());
    }
}