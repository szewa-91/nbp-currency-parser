package pl.parser.nbp.snapshot;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class ParallelCurrenciesSnapshotService implements CurrenciesSnapshotService {
    private static final String FILE = "c073z070413";

    private CurrenciesSnapshotProvider currenciesSnapshotProvider;

    @Autowired
    ParallelCurrenciesSnapshotService(CurrenciesSnapshotProvider currenciesSnapshotProvider) {
        this.currenciesSnapshotProvider = currenciesSnapshotProvider;
    }

    public Collection<CurrencySnapshot> getCurrenciesSnapshots() {
        return currenciesSnapshotProvider.getCurrencies(FILE).getPositions();
    }
}
