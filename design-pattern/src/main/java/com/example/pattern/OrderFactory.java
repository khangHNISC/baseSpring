package com.example.pattern;

import static com.example.pattern.OrderEnum.*;

public class OrderFactory implements AbstractOrderFactory{

    @Override
    public CryptoStrategy getOrderInstance(OrderEnum orderId) {
        switch (orderId) {
            case BAKE: return new BakeStrategy();
            case SWAPCAKE: return new CakeStrategy();
            case BITCOIN: return new BtcStrategy();
            case ETHERUM: return new EthStrategy();

        }
        return null;
    }
}
