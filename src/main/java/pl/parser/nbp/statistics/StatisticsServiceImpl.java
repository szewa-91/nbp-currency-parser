package pl.parser.nbp.statistics;

import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.CurrencySnapshot;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.toList;

public class StatisticsServiceImpl implements StatisticsService {
    private static final int CALCULATION_SCALE = 6;
    private static final int RESULT_SCALE = 4;
    private static final UnaryOperator<BigDecimal> TO_SQUARE = val -> val.pow(2);

    @Override
    public CurrencyStatistics calculateStatistics(Collection<CurrencySnapshot> currencySnapshots) {
        CurrencyStatistics currencyStatistics = new CurrencyStatistics();
        currencyStatistics.setMeanBuyRate(computeMeanBuyRate(currencySnapshots));
        currencyStatistics.setSellRateStandardDeviation(computeSellRateStandardDeviation(currencySnapshots));
        return currencyStatistics;
    }

    private BigDecimal computeSellRateStandardDeviation(Collection<CurrencySnapshot> currencySnapshots) {
        List<BigDecimal> sellRates = currencySnapshots.stream()
                .filter(currencySnapshot -> currencySnapshot.getSellRate() != null)
                .map(CurrencySnapshot::getSellRate)
                .collect(toList());

        if (sellRates.isEmpty()) {
            return null;
        }

        return calculateStandardDeviation(sellRates).setScale(RESULT_SCALE, RoundingMode.HALF_EVEN);
    }

    private BigDecimal computeMeanBuyRate(Collection<CurrencySnapshot> currencySnapshots) {
        List<BigDecimal> buyRates = currencySnapshots.stream()
                .filter(currencySnapshot -> currencySnapshot.getBuyRate() != null)
                .map(CurrencySnapshot::getBuyRate)
                .collect(toList());

        if (buyRates.isEmpty()) {
            return null;
        }

        return computeMean(buyRates).setScale(RESULT_SCALE, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateStandardDeviation(List<BigDecimal> numbers) {
        BigDecimal meanValue = computeMean(numbers);
        BigDecimal variance = numbers.stream()
                .map(val -> val.subtract(meanValue))
                .map(TO_SQUARE)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(numbers.size()), CALCULATION_SCALE, RoundingMode.HALF_EVEN);
        return variance.sqrt(MathContext.DECIMAL32).setScale(CALCULATION_SCALE, RoundingMode.HALF_EVEN);
    }


    private static BigDecimal computeMean(List<BigDecimal> buyRates) {
        return buyRates
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(buyRates.size()), CALCULATION_SCALE, RoundingMode.HALF_EVEN);
    }
}
