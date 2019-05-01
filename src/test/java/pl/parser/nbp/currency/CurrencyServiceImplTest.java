package pl.parser.nbp.currency;

import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshot;
import pl.parser.nbp.statistics.StatisticsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyServiceImplTest {
    private static final LocalDate START_DATE = LocalDate.of(2019, 1, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 1, 15);
    private static final CurrencyStatistics CURRENCY_STATISTICS = new CurrencyStatistics();
    private static final CurrencySnapshot USD_SNAPSHOT_1 = currencySnapshot("USD", "4.0", "4.2");
    private static final CurrencySnapshot EUR_SNAPSHOT = currencySnapshot("EUR", "4.5", "4.6");
    private static final CurrencySnapshot USD_SNAPSHOT_2 = currencySnapshot("USD", "4.2", "4.4");
    private static final CurrencySnapshot HUF_SNAPSHOT = currencySnapshot("HUF", "0.013", "0.015");

    private CurrencyService currencyService;
    private CurrenciesSnapshotService currenciesSnapshotService = mock(CurrenciesSnapshotService.class);
    private StatisticsService statisticsService = mock(StatisticsService.class);

    @Before
    public void setUp() {
        currencyService = new CurrencyServiceImpl(currenciesSnapshotService, statisticsService);
    }

    @Test
    public void shouldGetStatisticsOnlyForGivenCurrency() {
        when(currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE))
                .thenReturn(asList(
                        response(USD_SNAPSHOT_1, EUR_SNAPSHOT),
                        response(USD_SNAPSHOT_2, HUF_SNAPSHOT)));
        when(statisticsService.calculateStatistics(containingBoth(USD_SNAPSHOT_1, USD_SNAPSHOT_2)))
                .thenReturn(CURRENCY_STATISTICS);

        CurrencyStatistics currencyStatistics =
                currencyService.getCurrencyStatistics("USD", START_DATE, END_DATE);

        assertThat(currencyStatistics).isSameAs(CURRENCY_STATISTICS);
    }

    private Collection<CurrencySnapshot> containingBoth(CurrencySnapshot snapshot1, CurrencySnapshot snapshot2) {
        return argThat(currencySnapshots -> currencySnapshots.contains(snapshot1)
                && currencySnapshots.contains(snapshot2));
    }

    private static CurrenciesSnapshotResponse response(CurrencySnapshot... currencySnapshots) {
        CurrenciesSnapshotResponse currenciesSnapshotResponse = new CurrenciesSnapshotResponse();
        currenciesSnapshotResponse.setPositions(asList(currencySnapshots));
        return currenciesSnapshotResponse;
    }

    private static CurrencySnapshot currencySnapshot(String code, String buyRate, String sellRate) {
        CurrencySnapshot currencySnapshot = new CurrencySnapshot();
        currencySnapshot.setCurrencyCode(code);
        currencySnapshot.setBuyRate(new BigDecimal(buyRate));
        currencySnapshot.setSellRate(new BigDecimal(sellRate));
        return currencySnapshot;
    }

}
