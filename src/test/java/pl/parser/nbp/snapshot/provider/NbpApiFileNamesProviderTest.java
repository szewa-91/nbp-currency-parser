package pl.parser.nbp.snapshot.provider;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

// this test uses external connection to NBP service,
// may fail in case of no connection or changes in files
public class NbpApiFileNamesProviderTest {
    private static final LocalDate START_DATE_2019 = LocalDate.of(2019, 1, 3);
    private static final LocalDate END_DATE_2019 = LocalDate.of(2019, 1, 7);
    private static final LocalDate START_DATE_2018 = LocalDate.of(2018, 12, 20);
    private static final LocalDate END_DATE_2018 = LocalDate.of(2018, 12, 27);
    private static final LocalDate START_DATE_BROKEN_NAME = LocalDate.of(2019, 1, 2);

    private NbpApiFileNamesProvider nbpApiFileNamesProvider = new NbpApiFileNamesProvider();

    @Test
    public void shouldGetFileNamesFromNbpForCurrentYear() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE_2019, END_DATE_2019);

        assertThat(fileNames).containsExactly("c002z190103", "c003z190104", "c004z190107");
    }

    @Test
    public void shouldGetFileNamesFromNbpForPastYear() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE_2018, END_DATE_2018);

        assertThat(fileNames).containsExactly("c247z181220", "c248z181221", "c249z181224", "c250z181227");
    }

    @Test
    public void shouldGetFileNamesFromNbpForMultipleYears() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE_2018, END_DATE_2019);

        assertThat(fileNames).containsExactly(
                "c247z181220", "c248z181221", "c249z181224", "c250z181227", "c251z181228",
                "c252z181231", "c001z190102", "c002z190103", "c003z190104", "c004z190107"
        );
    }

    @Test
    public void shouldFixBrokenFileName() {
        Collection<String> fileNames = nbpApiFileNamesProvider.getFileNames(START_DATE_BROKEN_NAME, END_DATE_2019);

        assertThat(fileNames).containsExactly("c001z190102", "c002z190103", "c003z190104", "c004z190107");
    }
}