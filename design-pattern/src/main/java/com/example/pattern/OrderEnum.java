package com.example.pattern;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum OrderEnum {
    SWAPCAKE(1, "CAKE"){
        @Override
        public String doSomething() {
            return "CAKE";
        }
    },
    BITCOIN(2, "BTC"){
        @Override
        public String doSomething() {
            return "BTC";
        }
    },
    ETHERUM(3, "ETH"){
        @Override
        public String doSomething() {
            return "ETH";
        }
    },
    BAKE(1){
        @Override
        public String doSomething() {
            return "BAKE";
        }
    };

    int id;
    String description;

    private OrderEnum(int id) {
    }


    public abstract String doSomething();
}
