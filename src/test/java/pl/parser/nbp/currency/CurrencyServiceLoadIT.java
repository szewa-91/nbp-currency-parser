package pl.parser.nbp.currency;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.parser.nbp.snapshot.CurrencySnapshotServiceFactory;
import pl.parser.nbp.snapshot.NbpCurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsServiceFactory;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore("Load test, run only manually")
public class CurrencyServiceLoadIT {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);
    private static final LocalDate END_DATE = LocalDate.of(2019, 4, 17);

    private CurrencyService currencyService;

    @Before
    public void setUp() {
        CurrencySnapshotServiceFactory currencySnapshotServiceFactory =
                new NbpCurrencySnapshotServiceFactory();
        StatisticsServiceFactory statisticsServiceFactory = new StatisticsServiceFactory();
        currencyService = new CurrencyServiceFactory(currencySnapshotServiceFactory, statisticsServiceFactory)
                .createCurrencyService();
    }

    @Test
    public void shouldReturnStatisticsForBigAmoutOfData() {
        CurrencyStatistics statistics = currencyService.getCurrencyStatistics("USD", START_DATE, END_DATE);
        assertThat(statistics.getAverageBuyRate()).isNotNull();
        assertThat(statistics.getSellRateStandardDeviation()).isNotNull();
    }
}