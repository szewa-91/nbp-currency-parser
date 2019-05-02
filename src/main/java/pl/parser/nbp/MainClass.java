package pl.parser.nbp;

import pl.parser.nbp.currency.CurrencyService;
import pl.parser.nbp.currency.CurrencyServiceFactory;
import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.NbpCurrencySnapshotServiceFactory;
import pl.parser.nbp.statistics.StatisticsServiceFactory;

import java.time.LocalDate;

public class MainClass {
    private static final int RETRY_ATTEMPTS = 10;
    private static final int RETRY_INTERVAL = 1000;

    public static void main(String[] args) {
        String currency = args[0];
        LocalDate startDate = LocalDate.parse(args[1]);
        LocalDate endDate = LocalDate.parse(args[2]);
        CurrencyStatistics statistics =
                getCurrencyService().getCurrencyStatistics(currency, startDate, endDate);
        System.out.println(statistics);
    }

    private static CurrencyService getCurrencyService() {
        CurrencyServiceFactory currencyServiceFactory = new CurrencyServiceFactory(
                new NbpCurrencySnapshotServiceFactory()
                        .withRetryAttempts(RETRY_ATTEMPTS).withRetryInterval(RETRY_INTERVAL),
                new StatisticsServiceFactory());

        return currencyServiceFactory.createCurrencyService();
    }
}
