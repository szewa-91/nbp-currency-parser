package pl.parser.nbp.snapshot.provider;

import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrencySnapshot;
import pl.parser.nbp.snapshot.FileNotLoadedException;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

// this test uses external connection to NBP service,
// may fail in case of no connection or changes in files
public class NbpApiCurrenciesSnapshotProviderTest {
    private static final String FILE = "c073z070413";
    private static final String NOT_EXISTING_FILE = "c173z980413";

    private NbpApiCurrenciesSnapshotProvider currenciesSnapshotReader;

    @Before
    public void setUp() {
        currenciesSnapshotReader = new NbpApiCurrenciesSnapshotProvider();
    }

    @Test
    public void shouldParseSnapshot() throws JAXBException {
        CurrenciesSnapshotResponse snapshotFromUrl = currenciesSnapshotReader.getCurrencies(FILE);

        assertThat(snapshotFromUrl.getTableNumber()).isEqualTo("73/C/NBP/2007");
        assertThat(snapshotFromUrl.getPublicationDate()).isEqualTo(LocalDate.of(2007, 4, 13));
        assertThat(snapshotFromUrl.getPositions())
                .extracting(CurrencySnapshot::getCurrencyCode, CurrencySnapshot::getBuyRate, CurrencySnapshot::getSellRate)
                .contains(
                        tuple("USD", new BigDecimal("2.8210"), new BigDecimal("2.8780")),
                        tuple("EUR", new BigDecimal("3.7976"), new BigDecimal("3.8744")),
                        tuple("CHF", new BigDecimal("2.3163"), new BigDecimal("2.3631"))
                );
    }

    @Test
    public void shouldThrowErrorForNotExistingFile() {
        assertThatThrownBy(() ->
                currenciesSnapshotReader.getCurrencies(NOT_EXISTING_FILE))
                .isInstanceOf(JAXBException.class);
    }
}