package pl.parser.nbp.snapshot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencySnapshotConfig {
    @Bean
    ParallelCurrenciesSnapshotService currenciesSnapshotService(CurrenciesSnapshotProvider currenciesSnapshotProvider) {
        return new ParallelCurrenciesSnapshotService(currenciesSnapshotProvider);
    }

    @Bean
    CurrenciesSnapshotProvider currenciesSnapshotProvider() {
        return new NbpApiCurrenciesSnapshotProvider();
    }
}
