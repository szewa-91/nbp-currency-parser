package pl.parser.nbp.snapshot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.parser.nbp.snapshot.provider.CurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.FileNamesProvider;
import pl.parser.nbp.snapshot.provider.NbpApiCurrenciesSnapshotProvider;
import pl.parser.nbp.snapshot.provider.MockFileNamesProvider;

@Configuration
public class CurrencySnapshotConfig {
    @Bean
    ParallelCurrenciesSnapshotService currenciesSnapshotService(FileNamesProvider fileNamesProvider, CurrenciesSnapshotProvider currenciesSnapshotProvider) {
        return new ParallelCurrenciesSnapshotService(fileNamesProvider, currenciesSnapshotProvider);
    }

    @Bean
    CurrenciesSnapshotProvider currenciesSnapshotProvider() {
        return new NbpApiCurrenciesSnapshotProvider();
    }

    @Bean
    FileNamesProvider fileNamesProvider() {
        return new MockFileNamesProvider();
    }
}
