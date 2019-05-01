package pl.parser.nbp.currency;

import pl.parser.nbp.snapshot.CurrenciesSnapshotService;

import java.time.LocalDate;

public class CurrencyServiceImpl implements CurrencyService {
    private CurrenciesSnapshotService currenciesSnapshotService;

    public CurrencyServiceImpl(CurrenciesSnapshotService currenciesSnapshotService) {
        this.currenciesSnapshotService = currenciesSnapshotService;
    }

    @Override
    public CurrencyStatistics getCurrencyStatistics(String currencyCode,
                                                    LocalDate startDate,
                                                    LocalDate endDate) {
        return new CurrencyStatistics();
    }
}
