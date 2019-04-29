package pl.parser.nbp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.parser.nbp.snapshot.CurrencySnapshotConfig;

@Configuration
@Import(CurrencySnapshotConfig.class)
class MainConfig {
}
