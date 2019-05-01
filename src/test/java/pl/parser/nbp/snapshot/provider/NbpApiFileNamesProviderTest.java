package pl.parser.nbp.snapshot.provider;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

// this test uses external connection to NBP service,
// may fail in case of no connection or changes in files
public class NbpApiFileNamesProviderTest {
    private static final LocalDate START_DATE = LocalDate.of(2019, 1, 2);
    private static final LocalDate START_DATE_BROKEN_NAME = LocalDate.of(2019, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2019, 1, 7);

    private NbpApiFileNamesProvider nbpApiFileNamesProvider = new NbpApiFileNamesProvider();

    @Test
    public void shouldGetFileNamesFromNbpAndFilter() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE, END_DATE);

        assertThat(fileNames).contains("c002z190103", "c003z190104", "c004z190107");
    }

    @Test
    public void shouldFixBrokenFileName() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE_BROKEN_NAME, END_DATE);

        assertThat(fileNames).contains("c001z190102", "c002z190103", "c003z190104", "c004z190107");
    }
}