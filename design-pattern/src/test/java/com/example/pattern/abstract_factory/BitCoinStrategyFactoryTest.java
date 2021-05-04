package com.example.pattern.abstract_factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitCoinStrategyFactoryTest {

    @Test
    void givenBake_isBakeStrategy() {
        AbstractStrategyFactory factory = new BitCoinStrategyFactory();
        assertTrue(factory.getStrategyInstance(BitCoinEnum.BAKE) instanceof BakeStrategy);
    }
}