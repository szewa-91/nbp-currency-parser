package pl.parser.nbp;

import pl.parser.nbp.currency.CurrencyService;
import pl.parser.nbp.currency.CurrencyServiceFactory;
import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.NbpCurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsServiceFactory;

import java.time.LocalDate;

public class MainClass {
    private static final LocalDate START_DATE = LocalDate.of(2019, 2, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 2, 20);

    public static void main(String[] args) {
        CurrencyStatistics statistics = getCurrencyService().getCurrencyStatistics("USD", START_DATE, END_DATE);
        System.out.println(statistics);
    }


    private static CurrencyService getCurrencyService() {
        CurrencyServiceFactory currencyServiceFactory = new CurrencyServiceFactory(
                new NbpCurrencySnapshotServiceFactory(),
                new StatisticsServiceFactory());

        return currencyServiceFactory.createCurrencyService();
    }
}
