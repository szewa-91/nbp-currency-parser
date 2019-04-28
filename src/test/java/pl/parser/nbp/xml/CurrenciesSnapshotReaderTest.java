package pl.parser.nbp.xml;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import pl.parser.nbp.model.CurrenciesSnapshotResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrenciesSnapshotReaderTest {
    private CurrenciesSnapshotReader currenciesSnapshotReader;

    @Before
    public void setUp() {
        currenciesSnapshotReader = new CurrenciesSnapshotReader(new RestTemplate());
    }

    @Test
    public void shouldParseSnapshot() {
        CurrenciesSnapshotResponse snapshotFromUrl = currenciesSnapshotReader.getSnapshotFromUrl("http://www.nbp.pl/kursy/xml/c073z070413.xml");

        assertThat(snapshotFromUrl).isEqualTo(new CurrenciesSnapshotResponse());
    }
}