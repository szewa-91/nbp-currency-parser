package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshot;
import pl.parser.nbp.statistics.StatisticsService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CurrencyServiceImpl implements CurrencyService {
    private CurrenciesSnapshotService currenciesSnapshotService;
    private StatisticsService statisticsService;

    public CurrencyServiceImpl(CurrenciesSnapshotService currenciesSnapshotService,
                               StatisticsService statisticsService) {
        this.currenciesSnapshotService = currenciesSnapshotService;
        this.statisticsService = statisticsService;
    }

    @Override
    public CurrencyStatistics getCurrencyStatistics(String currencyCode,
                                                    LocalDate startDate,
                                                    LocalDate endDate) {
        var currenciesSnapshots = currenciesSnapshotService.getCurrenciesSnapshots(startDate, endDate);
        var snapshotsForCurrency = currenciesSnapshots.stream()
                .map(CurrenciesSnapshotResponse::getPositions)
                .flatMap(Collection::stream)
                .filter(byCurrency(currencyCode))
                .collect(Collectors.toList());
        return statisticsService.calculateStatistics(snapshotsForCurrency);
    }

    private Predicate<CurrencySnapshot> byCurrency(String currencyCode) {
        return currencySnapshot -> currencyCode.equals(currencySnapshot.getCurrencyCode());
    }
}
