package pl.parser.nbp.currency;

import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.TestCurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsServiceFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyServiceE2EIT {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);
    private static final LocalDate END_DATE = LocalDate.of(2007, 4, 17);

    private CurrencyService currencyService;

    @Before
    public void setUp() {
        TestCurrencySnapshotServiceFactory currencySnapshotServiceFactory =
                new TestCurrencySnapshotServiceFactory();
        StatisticsServiceFactory statisticsServiceFactory = new StatisticsServiceFactory();
        currencyService = new CurrencyServiceFactory(currencySnapshotServiceFactory, statisticsServiceFactory)
                .createCurrencyService();
    }

    @Test
    public void shouldReturnStatisticsForUsd() {
        assertThat(currencyService.getCurrencyStatistics("USD", START_DATE, END_DATE))
                .extracting(CurrencyStatistics::getCurrencyCode,
                        CurrencyStatistics::getAverageBuyRate,
                        CurrencyStatistics::getSellRateStandardDeviation)
                .containsExactly(
                        "USD",
                        new BigDecimal("2.8310"),
                        new BigDecimal("0.0100")
                );
    }

    @Test
    public void shouldReturnStatisticsForEur() {
        assertThat(currencyService.getCurrencyStatistics("EUR", START_DATE, END_DATE))
                .extracting(CurrencyStatistics::getCurrencyCode,
                        CurrencyStatistics::getAverageBuyRate,
                        CurrencyStatistics::getSellRateStandardDeviation)
                .containsExactly(
                        "EUR",
                        new BigDecimal("3.8302"),
                        new BigDecimal("0.0480")
                );
    }
}