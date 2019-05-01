package pl.parser.nbp.statistics;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.CurrencySnapshot;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class StatisticsServiceImplTest {
    private StatisticsService statisticsService = new StatisticsServiceImpl();

    private SoftAssertions softAssert = new SoftAssertions();

    @Test
    public void shouldCalculateStatistics() {
        List<CurrencySnapshot> currencySnapshots = asList(
                currencySnapshot("USD", "4.4", "4.7"),
                currencySnapshot("USD", "4.6", "4.8"),
                currencySnapshot("USD", "4.9", "5.0")
        );

        CurrencyStatistics currencyStatistics = statisticsService.calculateStatistics(currencySnapshots);

        softAssert.assertThat(currencyStatistics.getAverageBuyRate())
                .isEqualByComparingTo(new BigDecimal("4.6333"));
        softAssert.assertThat(currencyStatistics.getSellRateStandardDeviation())
                .isEqualByComparingTo(new BigDecimal("0.1249"));

        softAssert.assertAll();
    }

    private CurrencySnapshot currencySnapshot(String code, String buyRate, String sellRate) {
        CurrencySnapshot currencySnapshot = new CurrencySnapshot();
        currencySnapshot.setCurrencyCode(code);
        currencySnapshot.setBuyRate(new BigDecimal(buyRate));
        currencySnapshot.setSellRate(new BigDecimal(sellRate));
        return currencySnapshot;
    }
}