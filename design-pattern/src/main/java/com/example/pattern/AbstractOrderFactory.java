package com.example.pattern;

public interface AbstractOrderFactory {
    public CryptoStrategy getOrderInstance(OrderEnum orderType);

}
