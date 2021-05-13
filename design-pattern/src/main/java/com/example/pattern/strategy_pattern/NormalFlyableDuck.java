package com.example.pattern.strategy_pattern;

import com.example.pattern.strategy_pattern.behavior.FlyBehavior;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NormalFlyableDuck extends Duck {

    public NormalFlyableDuck(FlyBehavior flyBehavior) {
        super(flyBehavior);
    }

    @Override
    public void display() {
        log.info("display normal ducks");
    }
}
