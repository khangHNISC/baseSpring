package com.example.pattern.abstract_factory;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BitCoinEnum {
    SWAPCAKE(1, "CAKE") {
        @Override
        public String doSomething() {
            return "CAKE";
        }
    },
    BITCOIN(2, "BTC") {
        @Override
        public String doSomething() {
            return "BTC";
        }
    },
    ETHERUM(3, "ETH") {
        @Override
        public String doSomething() {
            return "ETH";
        }
    },
    BAKE(1) {
        @Override
        public String doSomething() {
            return "BAKE";
        }
    };

    int id;
    String description;

    BitCoinEnum(int id) {
        this.id = id;
    }


    public abstract String doSomething();
}
