package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;
import pl.parser.nbp.snapshot.provider.MockCurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.MockFileNamesProvider;

public class TestCurrencySnapshotServiceFactory extends CurrencySnapshotServiceFactory {
    @Override
    protected FileNamesProvider createFileNamesProvider() {
        return new MockFileNamesProvider();
    }

    @Override
    protected CurrenciesSnapshotProvider createCurrenciesSnapshotProvider() {
        return new MockCurrenciesSnapshotProvider();
    }
}
