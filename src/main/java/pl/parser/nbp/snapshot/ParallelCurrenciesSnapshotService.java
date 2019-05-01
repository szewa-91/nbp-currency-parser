package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelCurrenciesSnapshotService implements CurrenciesSnapshotService {
    private FileNamesProvider fileNamesProvider;
    private CurrenciesSnapshotProvider currenciesSnapshotProvider;

    ParallelCurrenciesSnapshotService(FileNamesProvider fileNamesProvider, CurrenciesSnapshotProvider currenciesSnapshotProvider) {
        this.fileNamesProvider = fileNamesProvider;
        this.currenciesSnapshotProvider = currenciesSnapshotProvider;
    }

    public List<CurrenciesSnapshotResponse> getCurrenciesSnapshots(LocalDate startDate, LocalDate endDate) {
        return fileNamesProvider.getFileNames(startDate, endDate).parallelStream()
                .map(fileName -> currenciesSnapshotProvider.getCurrencies(fileName))
                .collect(Collectors.toList());
    }
}
