package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsService;
import pl.parser.nbp.statistics.StatisticsServiceFactory;

public class CurrencyServiceFactory {
    private CurrencySnapshotServiceFactory currencySnapshotServiceFactory;
    private StatisticsServiceFactory statisticsServiceFactory;

    public CurrencyServiceFactory(CurrencySnapshotServiceFactory currencySnapshotServiceFactory, StatisticsServiceFactory statisticsServiceFactory) {
        this.currencySnapshotServiceFactory = currencySnapshotServiceFactory;
        this.statisticsServiceFactory = statisticsServiceFactory;
    }

    public CurrencyService createCurrencyService() {
        CurrenciesSnapshotService currenciesSnapshotService =
                currencySnapshotServiceFactory.createCurrenciesSnapshotService();
        StatisticsService statisticsService = statisticsServiceFactory.createStatisticsService();
        return new CurrencyServiceImpl(currenciesSnapshotService, statisticsService);
    }
}
