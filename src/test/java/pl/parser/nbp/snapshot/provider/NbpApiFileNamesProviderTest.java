package pl.parser.nbp.snapshot.provider;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

// this test uses external connection to NBP service,
// may fail in case of no connection or changes in files
public class NbpApiFileNamesProviderTest {
    private static final LocalDate START_DATE = LocalDate.of(2019, 1, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 1, 15);
    private NbpApiFileNamesProvider nbpApiFileNamesProvider = new NbpApiFileNamesProvider();

    @Test
    public void shouldGetFileNamesFromNbpAndFilter() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE, END_DATE);
        assertThat(fileNames).contains("c007z190110", "c008z190111", "c009z190114", "c010z190115");
    }
}