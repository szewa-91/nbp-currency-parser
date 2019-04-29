package pl.parser.nbp.snapshot.provider;

import java.time.LocalDate;
import java.util.Collection;

public interface FileNamesProvider {
    Collection<String> getFileNames(LocalDate startDate, LocalDate endDate);
}
