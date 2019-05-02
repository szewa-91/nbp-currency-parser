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
        CurrencyStatistics statistics = currencyService.getCurrencyStatistics("USD", START_DATE, END_DATE);
        assertThat(statistics.getAverageBuyRate())
                .isEqualTo(new BigDecimal("2.8310"));
        assertThat(statistics.getSellRateStandardDeviation())
                .isEqualTo(new BigDecimal("0.0082"));
    }

    @Test
    public void shouldReturnStatisticsForEur() {
        CurrencyStatistics statistics = currencyService.getCurrencyStatistics("EUR", START_DATE, END_DATE);
        assertThat(statistics.getAverageBuyRate())
                .isEqualTo(new BigDecimal("3.8302"));
        assertThat(statistics.getSellRateStandardDeviation())
                .isEqualTo(new BigDecimal("0.0476"));
    }
}