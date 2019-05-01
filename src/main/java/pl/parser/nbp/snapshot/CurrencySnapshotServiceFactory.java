package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

public abstract class CurrencySnapshotServiceFactory {
    public CurrenciesSnapshotService currenciesSnapshotService() {
        return new ParallelCurrenciesSnapshotService(
                createFileNamesProvider(),
                createCurrenciesSnapshotProvider());
    }

    abstract FileNamesProvider createFileNamesProvider();
    abstract CurrenciesSnapshotProvider createCurrenciesSnapshotProvider();

}
