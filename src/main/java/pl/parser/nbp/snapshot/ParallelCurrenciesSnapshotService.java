package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParallelCurrenciesSnapshotService implements CurrenciesSnapshotService {
    private FileNamesProvider fileNamesProvider;
    private CurrenciesSnapshotProvider currenciesSnapshotProvider;

    ParallelCurrenciesSnapshotService(FileNamesProvider fileNamesProvider, CurrenciesSnapshotProvider currenciesSnapshotProvider) {
        this.fileNamesProvider = fileNamesProvider;
        this.currenciesSnapshotProvider = currenciesSnapshotProvider;
    }

    public Collection<CurrenciesSnapshotResponse> getCurrenciesSnapshots(LocalDate startDate, LocalDate endDate) {
        return fileNamesProvider.getFileNames(startDate, endDate).parallelStream()
                .map(getCurrenciesWithRetry(5))
                .collect(Collectors.toList());
    }

    private Function<String, CurrenciesSnapshotResponse> getCurrenciesWithRetry(int retryAttempts) {
        return fileName -> {
            try {
                return currenciesSnapshotProvider.getCurrencies(fileName);
            } catch (JAXBException e) {
                if (retryAttempts == 0) {
                    throw new RuntimeException(e);
                }
                System.out.println("Retrying...");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Function<String, CurrenciesSnapshotResponse> currenciesWithRetry = getCurrenciesWithRetry(retryAttempts - 1);
            return currenciesWithRetry.apply(fileName);
        };
    }
}
