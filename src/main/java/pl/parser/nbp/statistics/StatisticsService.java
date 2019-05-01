package pl.parser.nbp.statistics;

import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.CurrencySnapshot;

import java.util.Collection;

public interface StatisticsService {
    CurrencyStatistics calculateStatistics(Collection<CurrencySnapshot> currencySnapshots);
}
