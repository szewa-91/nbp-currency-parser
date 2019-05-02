package pl.parser.nbp.snapshot;

import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParallelCurrenciesSnapshotServiceTest {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);
    private static final LocalDate END_DATE = LocalDate.of(2007, 4, 14);
    private static final String FILENAME_1 = "c073z070413.xml";
    private static final String FILENAME_2 = "c073z070414.xml";
    private static final CurrenciesSnapshotResponse RESPONSE_1 = new CurrenciesSnapshotResponse();
    private static final CurrenciesSnapshotResponse RESPONSE_2 = new CurrenciesSnapshotResponse();
    private static final String EXCEPTION_MESSAGE = "Couldn't connect";

    private CurrenciesSnapshotService currenciesSnapshotService;
    private FileNamesProvider fileNamesProvider = mock(FileNamesProvider.class);
    private CurrenciesSnapshotProvider currenciesSnapshotProvider = mock(CurrenciesSnapshotProvider.class);

    @Before
    public void setUp() {
        currenciesSnapshotService = new ParallelCurrenciesSnapshotService(fileNamesProvider, currenciesSnapshotProvider);

    }

    @Test
    public void shouldReturnCurrenciesSnapshots() throws JAXBException {
        givenFileNames(FILENAME_1, FILENAME_2);
        when(currenciesSnapshotProvider.getCurrencies(FILENAME_1)).thenReturn(RESPONSE_1);
        when(currenciesSnapshotProvider.getCurrencies(FILENAME_2)).thenReturn(RESPONSE_2);

        Collection<CurrenciesSnapshotResponse> currenciesSnapshotResponse =
                currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE);

        assertThat(currenciesSnapshotResponse)
                .containsExactly(RESPONSE_1, RESPONSE_2);

    }

    @Test
    public void shouldRetryFiveTimesAfterException() throws JAXBException {
        givenFileNames(FILENAME_1);
        when(currenciesSnapshotProvider.getCurrencies(FILENAME_1))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenReturn(RESPONSE_1);

        Collection<CurrenciesSnapshotResponse> currenciesSnapshotResponse =
                currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE);

        assertThat(currenciesSnapshotResponse)
                .containsExactly(RESPONSE_1);

    }

    @Test
    public void shouldThrowExceptionWhenNoResponseAfterFifthRetry() throws JAXBException {
        givenFileNames(FILENAME_1);
        when(currenciesSnapshotProvider.getCurrencies(FILENAME_1))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenThrow(new JAXBException(EXCEPTION_MESSAGE))
                .thenReturn(RESPONSE_1);

        assertThatThrownBy(() -> currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE))
                .isInstanceOf(RuntimeException.class)
                .hasMessageStartingWith("javax.xml.bind.JAXBException");
    }

    private void givenFileNames(String... fileNames) {
        when(fileNamesProvider.getFileNames(START_DATE, END_DATE))
                .thenReturn(Arrays.asList(fileNames));
    }
}