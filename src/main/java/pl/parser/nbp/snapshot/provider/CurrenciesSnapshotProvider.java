package pl.parser.nbp.snapshot.provider;

import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;

public interface CurrenciesSnapshotProvider {
    CurrenciesSnapshotResponse getCurrencies(String urlName);

}
