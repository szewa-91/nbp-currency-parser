package pl.parser.nbp.snapshot;

import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;

public abstract class CurrencySnapshotServiceFactory {
    private static final int DEFAULT_RETRY_ATTEMPTS = 5;
    private static final int DEFAULT_RETRY_INTERVAL = 1000;
    private int retryAttempts = DEFAULT_RETRY_ATTEMPTS;
    private int retryInterval = DEFAULT_RETRY_INTERVAL;

    public CurrenciesSnapshotService createCurrenciesSnapshotService() {
        return new ParallelCurrenciesSnapshotService(
                createFileNamesProvider(),
                createCurrenciesSnapshotProvider(), retryAttempts, retryInterval);
    }

    public CurrencySnapshotServiceFactory withRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
        return this;
    }

    public CurrencySnapshotServiceFactory withRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
        return this;
    }

    protected abstract FileNamesProvider createFileNamesProvider();

    protected abstract CurrenciesSnapshotProvider createCurrenciesSnapshotProvider();

}
