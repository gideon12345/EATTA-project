package com.kiprono.currencyexchange;

public class Currencies {
    private String curr;
    private Double values;

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public Double getValues() {
        return values;
    }

    public void setValues(Double values) {
        this.values = values;
    }

    public Currencies() {
    }

    public Currencies(String curr, Double values) {
        this.curr = curr;
        this.values = values;
    }
}
