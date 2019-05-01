package pl.parser.nbp.snapshot;

import java.time.LocalDate;
import java.util.Collection;

public interface CurrenciesSnapshotService {
    Collection<CurrenciesSnapshotResponse> getCurrenciesSnapshots(LocalDate startDate, LocalDate endDate);
}
