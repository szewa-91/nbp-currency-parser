package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrencySnapshotServiceFactory;

public class TestCurrencyServiceFactory extends CurrencyServiceFactory {
    public TestCurrencyServiceFactory(CurrencySnapshotServiceFactory currencySnapshotServiceFactory) {
        super(currencySnapshotServiceFactory);
    }
}
