package pl.parser.nbp.snapshot;

import java.time.LocalDate;
import java.util.List;

public interface CurrenciesSnapshotService {
    List<CurrenciesSnapshotResponse> getCurrenciesSnapshots(LocalDate startDate, LocalDate endDate);
}
