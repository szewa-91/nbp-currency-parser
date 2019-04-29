package pl.parser.nbp.snapshot;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ParallelCurrenciesSnapshotServiceIT {
    private static final String FILE = "c073z070413";

    private CurrenciesSnapshotService currenciesSnapshotService;

    @Before
    public void setUp() throws Exception {
        CurrenciesSnapshotProvider currenciesSnapshotProvider = new MockCurrenciesSnapshotProvider();
        currenciesSnapshotService = new ParallelCurrenciesSnapshotService(currenciesSnapshotProvider);
    }

    @Test
    public void getCurrencySnapshots() {
        Collection<CurrencySnapshot> currencySnapshots = currenciesSnapshotService.getCurrenciesSnapshots();
        assertThat(currencySnapshots).hasSize(3);
        assertThat(currencySnapshots)
                .extracting(CurrencySnapshot::getCurrencyCode, CurrencySnapshot::getBuyRate, CurrencySnapshot::getSellRate)
                .contains(
                        tuple("USD", new BigDecimal("2.8210"), new BigDecimal("2.8780")),
                        tuple("EUR", new BigDecimal("3.7976"), new BigDecimal("3.8744")),
                        tuple("CHF", new BigDecimal("2.3163"), new BigDecimal("2.3631"))
                );

    }
}