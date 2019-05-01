package pl.parser.nbp.currency;

import org.assertj.core.internal.BigDecimals;
import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.TestCurrencySnapshotServiceFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyServiceE2EIT {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);

    private CurrencyService currencyService;

    @Before
    public void setUp() {
        TestCurrencySnapshotServiceFactory currencySnapshotServiceFactory =
                new TestCurrencySnapshotServiceFactory();
        currencyService = new CurrencyServiceFactory(currencySnapshotServiceFactory)
                .createCurrencyService();
    }

    @Test
    public void shouldReturnStatisticsForCurrency() {
        assertThat(currencyService.getCurrencyStatistics("USD", START_DATE, START_DATE))
                .extracting(CurrencyStatistics::getCurrencyCode,
                        CurrencyStatistics::getAverageBuyRate,
                        CurrencyStatistics::getSellRateStandardDeviation)
                .containsExactly(
                        "USD",
                        new BigDecimal("2.8310"),
                        new BigDecimal("0.0100")
                );
    }
}