package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;
import pl.parser.nbp.snapshot.provider.NbpApiCurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.NbpApiFileNamesProvider;

public class NbpCurrencySnapshotServiceFactory extends CurrencySnapshotServiceFactory {
    @Override
    protected FileNamesProvider createFileNamesProvider() {
        return new NbpApiFileNamesProvider();
    }

    @Override
    protected CurrenciesSnapshotProvider createCurrenciesSnapshotProvider() {
        return new NbpApiCurrenciesSnapshotProvider();
    }
}
