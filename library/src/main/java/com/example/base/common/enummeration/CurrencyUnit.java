package com.example.base.common.enummeration;

public enum CurrencyUnit {
    VND{
        @Override
        public String getLangCode() {
            return "vi";
        }
        @Override
        public String getCountryCode() {
            return "VN";
        }
    };
    public abstract String getLangCode();
    public abstract String getCountryCode();
}
