package pl.parser.nbp.currency;


import java.time.LocalDate;

public interface CurrencyService {
    CurrencyStatistics getCurrencyStatistics(String currencyCode,
                                             LocalDate startDate,
                                             LocalDate endDate);
}
