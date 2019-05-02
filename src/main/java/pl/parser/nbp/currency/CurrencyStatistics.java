package pl.parser.nbp.currency;

import java.math.BigDecimal;

public class CurrencyStatistics {
    private BigDecimal averageBuyRate;
    private BigDecimal sellRateStandardDeviation;

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
                ", averageBuyRate=" + averageBuyRate +
                ", sellRateStandardDeviation=" + sellRateStandardDeviation +
                '}';
    }
}
