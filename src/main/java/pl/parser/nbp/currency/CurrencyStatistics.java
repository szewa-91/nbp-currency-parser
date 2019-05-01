package pl.parser.nbp.currency;

import java.math.BigDecimal;

public class CurrencyStatistics {
    private String currencyCode;
    private BigDecimal averageBuyRate;
    private BigDecimal sellRateStandardDeviation;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAverageBuyRate() {
        return averageBuyRate;
    }

    public void setMeanBuyRate(BigDecimal averageBuyRate) {
        this.averageBuyRate = averageBuyRate;
    }

    public BigDecimal getSellRateStandardDeviation() {
        return sellRateStandardDeviation;
    }

    public void setSellRateStandardDeviation(BigDecimal sellRateStandardDeviation) {
        this.sellRateStandardDeviation = sellRateStandardDeviation;
    }

    @Override
    public String toString() {
        return "CurrencyStatistics{" +
                "currencyCode='" + currencyCode + '\'' +
                ", averageBuyRate=" + averageBuyRate +
                ", sellRateStandardDeviation=" + sellRateStandardDeviation +
                '}';
    }
}
