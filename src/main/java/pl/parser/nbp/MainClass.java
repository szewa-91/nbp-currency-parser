package pl.parser.nbp;

import pl.parser.nbp.currency.CurrencyService;
import pl.parser.nbp.currency.CurrencyServiceFactory;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.NbpCurrencySnapshotServiceFactory;

import java.time.LocalDate;
import java.util.List;

public class MainClass {
    private static final LocalDate START_DATE = LocalDate.of(2019, 2, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 2, 20);

    public static void main(String[] args) {
        getCurrencyService().getCurrencyStatistics("USD", START_DATE, END_DATE);
    }

    private static CurrencyService getCurrencyService() {
        NbpCurrencySnapshotServiceFactory currenciesSnapshotService =
                new NbpCurrencySnapshotServiceFactory();
        CurrencyServiceFactory currencyServiceFactory = new CurrencyServiceFactory(currenciesSnapshotService);

        return currencyServiceFactory.createCurrencyService();
    }
}
