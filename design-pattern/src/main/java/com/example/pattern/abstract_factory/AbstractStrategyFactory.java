package com.example.pattern.abstract_factory;

public interface AbstractStrategyFactory {
    CryptoStrategy getStrategyInstance(BitCoinEnum orderType);
}
