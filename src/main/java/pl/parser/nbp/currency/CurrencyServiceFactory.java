package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsService;
import pl.parser.nbp.statistics.StatisticsServiceImpl;

public class CurrencyServiceFactory {
    private CurrencySnapshotServiceFactory currencySnapshotServiceFactory;

    public CurrencyServiceFactory(CurrencySnapshotServiceFactory currencySnapshotServiceFactory) {
        this.currencySnapshotServiceFactory = currencySnapshotServiceFactory;
    }

    public CurrencyService createCurrencyService() {
        CurrenciesSnapshotService currenciesSnapshotService =
                currencySnapshotServiceFactory.createCurrenciesSnapshotService();
        StatisticsService statisticsService = new StatisticsServiceImpl();
        return new CurrencyServiceImpl(currenciesSnapshotService, statisticsService);
    }
}
