package pl.parser.nbp.snapshot.provider;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class MockFileNamesProvider implements FileNamesProvider {
    private static final String FILE = "c073z070413";

    @Override
    public Collection<String> getFileNames(LocalDate startDate, LocalDate endDate) {
        return Collections.singletonList(FILE);
    }
}
