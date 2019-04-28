package pl.parser.nbp;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.parser.nbp.xml.CurrenciesSnapshotReader;

@Configuration
class NbpCurrencyParserConfig {
    @Bean
    CurrenciesSnapshotReader currenciesSnapshotReader() {
        return new CurrenciesSnapshotReader(new RestTemplate());
    }
}
