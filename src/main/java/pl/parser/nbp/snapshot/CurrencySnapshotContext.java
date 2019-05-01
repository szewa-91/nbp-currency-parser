package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;
import pl.parser.nbp.snapshot.provider.NbpApiCurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.NbpApiFileNamesProvider;

public class CurrencySnapshotContext {
    public ParallelCurrenciesSnapshotService currenciesSnapshotService() {
        return new ParallelCurrenciesSnapshotService(fileNamesProvider(), currenciesSnapshotProvider());
    }

    private CurrenciesSnapshotProvider currenciesSnapshotProvider() {
        return new NbpApiCurrenciesSnapshotProvider();
    }

    private FileNamesProvider fileNamesProvider() {
        return new NbpApiFileNamesProvider();
    }
}
