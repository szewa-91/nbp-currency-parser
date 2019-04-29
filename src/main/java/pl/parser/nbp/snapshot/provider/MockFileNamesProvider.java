package pl.parser.nbp.snapshot.provider;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class MockFileNamesProvider implements FileNamesProvider {
    private static final List<String> FILES = asList("c073z070413", "c073z070416", "c073z070417");

    @Override
    public Collection<String> getFileNames(LocalDate startDate, LocalDate endDate) {
        return FILES;
    }
}
