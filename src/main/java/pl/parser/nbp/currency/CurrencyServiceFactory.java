package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshotServiceFactory;

public class CurrencyServiceFactory {
    private CurrencySnapshotServiceFactory currencySnapshotServiceFactory;

    public CurrencyServiceFactory(CurrencySnapshotServiceFactory currencySnapshotServiceFactory) {
        this.currencySnapshotServiceFactory = currencySnapshotServiceFactory;
    }

    public CurrencyService createCurrencyService() {
        CurrenciesSnapshotService currenciesSnapshotService =
                currencySnapshotServiceFactory.createCurrenciesSnapshotService();
        return new CurrencyServiceImpl(currenciesSnapshotService);
    }
}
