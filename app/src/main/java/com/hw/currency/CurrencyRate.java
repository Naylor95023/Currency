package com.hw.currency;

public class CurrencyRate {

    private String Currency;
    private String CashBuyRate;
    private String CashSoldRate;

    public CurrencyRate(String Currency,String CashBuyRate,String CashSoldRate){

        this.Currency = Currency;
        this.CashBuyRate = CashBuyRate;
        this.CashSoldRate = CashSoldRate;

    }
    public CurrencyRate(){
        this.Currency = "";
        this.CashBuyRate = "";
        this.CashSoldRate = "";
    }

    public String getCashSoldRate() {
        return CashSoldRate;
    }

    public void setCashSoldRate(String cashSoldRate) {
        CashSoldRate = cashSoldRate;
    }

    public String getCashBuyRate() {
        return CashBuyRate;
    }

    public void setCashBuyRate(String cashBuyRate) {
        CashBuyRate = cashBuyRate;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
