package com.example.pattern.abstract_factory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BitCoinStrategyFactory implements AbstractStrategyFactory {

    @Override
    public CryptoStrategy getStrategyInstance(BitCoinEnum bitCoin) {
        switch (bitCoin) {
            case BAKE:
                return new BakeStrategy();
            case SWAPCAKE:
                return new CakeStrategy();
            case BITCOIN:
                return new BtcStrategy();
            case ETHERUM:
                return new EthStrategy();
            default:
                throw new IllegalArgumentException("invalid enum" + bitCoin);
        }
    }
}
