package pl.parser.nbp.snapshot;

import org.assertj.core.api.ListAssert;
import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;
import pl.parser.nbp.snapshot.provider.MockCurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.MockFileNamesProvider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;

public class CurrenciesSnapshotServiceIT {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);
    private static final LocalDate END_DATE = LocalDate.of(2007, 4, 17);

    private CurrenciesSnapshotService currenciesSnapshotService;

    @Before
    public void setUp() {
        currenciesSnapshotService = new TestCurrencySnapshotServiceFactory()
                .createCurrenciesSnapshotService();
    }

    @Test
    public void shouldReturnCurrenciesSnapshots() {
        Collection<CurrenciesSnapshotResponse> currenciesSnapshotResponse =
                currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE);

        assertAllCurrencySnapshots(currenciesSnapshotResponse).hasSize(9);
        assertAllCurrencySnapshots(currenciesSnapshotResponse)
                .extracting(CurrencySnapshot::getCurrencyCode, CurrencySnapshot::getBuyRate, CurrencySnapshot::getSellRate)
                .contains(
                        tuple("USD", new BigDecimal("2.8210"), new BigDecimal("2.8780")),
                        tuple("EUR", new BigDecimal("3.7976"), new BigDecimal("3.8744")),
                        tuple("CHF", new BigDecimal("2.3163"), new BigDecimal("2.3631"))
                );
    }

    private ListAssert<CurrencySnapshot> assertAllCurrencySnapshots(Collection<CurrenciesSnapshotResponse> currenciesSnapshotResponse) {
        return assertThat(allCurrencySnapshots(currenciesSnapshotResponse));
    }

    private List<CurrencySnapshot> allCurrencySnapshots(Collection<CurrenciesSnapshotResponse> currenciesSnapshotResponse) {
        return currenciesSnapshotResponse.stream().flatMap(response -> response.getPositions().stream()).collect(Collectors.toList());
    }
}

