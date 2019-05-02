package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParallelCurrenciesSnapshotService implements CurrenciesSnapshotService {
    private final int retryAttempts;
    private final int retryInterval;
    private FileNamesProvider fileNamesProvider;
    private CurrenciesSnapshotProvider currenciesSnapshotProvider;

    ParallelCurrenciesSnapshotService(FileNamesProvider fileNamesProvider, CurrenciesSnapshotProvider currenciesSnapshotProvider, int retryAttempts, int retryInterval) {
        this.fileNamesProvider = fileNamesProvider;
        this.currenciesSnapshotProvider = currenciesSnapshotProvider;
        this.retryAttempts = retryAttempts;
        this.retryInterval = retryInterval;
    }

    public Collection<CurrenciesSnapshotResponse> getCurrenciesSnapshots(LocalDate startDate, LocalDate endDate) {
        return fileNamesProvider.getFileNames(startDate, endDate).parallelStream()
                .map(getCurrenciesWithRetry(retryAttempts))
                .collect(Collectors.toList());
    }

    private Function<String, CurrenciesSnapshotResponse> getCurrenciesWithRetry(int retryAttemptsLeft) {
        return fileName -> {
            try {
                return currenciesSnapshotProvider.getCurrencies(fileName);
            } catch (JAXBException e) {
                if (retryAttemptsLeft == 0) {
                    throw new RuntimeException(e);
                }
                System.out.println("Retrying...");
            }
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getCurrenciesWithRetry(retryAttemptsLeft - 1).apply(fileName);
        };
    }
}
