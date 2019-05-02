package pl.parser.nbp.snapshot.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NbpApiFileNamesProvider implements FileNamesProvider {
    private static final String DIR_FILE = "https://www.nbp.pl/kursy/xml/dir%s.txt";
    private static final String FILENAME_PREFIX = "c";
    private static final int DATE_SUBSTRING_BEGIN_INDEX = 5;
    private static final String UNNECESARY_WHITESPACE_PREFIX = "\uFEFF";
    private static final String DATE_PATTERN_IN_FILENAME = "yyMMdd";
    private static final String CURRENT_YEAR_STRING = "";

    @Override
    public Collection<String> getFileNames(LocalDate startDate, LocalDate endDate) {
        return allFileNames(startDate, endDate)
                .filter(filterByPrefix().and(filterByDate(startDate, endDate)))
                .collect(Collectors.toList());
    }

    private Stream<String> allFileNames(LocalDate startDate, LocalDate endDate) {
        return resolveYears(startDate, endDate).stream()
                .map(this::getFileNamesForYear)
                .flatMap(Collection::stream)
                .map(removeLeadingWhitespace());
    }

    private Collection<String> getFileNamesForYear(String year) {
        try {
            URL url = new URL(String.format(DIR_FILE, year));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Function<String, String> removeLeadingWhitespace() {
        return fileName -> fileName.startsWith(UNNECESARY_WHITESPACE_PREFIX)
                ? fileName.substring(1)
                : fileName;
    }

    private Collection<String> resolveYears(LocalDate startDate, LocalDate endDate) {
        int currentYear = LocalDate.now().getYear();
        return IntStream.rangeClosed(startDate.getYear(), endDate.getYear())
                .mapToObj(getYearString(currentYear))
                .collect(Collectors.toList());
    }

    private IntFunction<String> getYearString(int currentYear) {
        return year -> currentYear == year
                ? CURRENT_YEAR_STRING
                : Integer.toString(year);
    }

    private static Predicate<String> filterByPrefix() {
        return fileName -> fileName.startsWith(FILENAME_PREFIX);
    }

    private static Predicate<String> filterByDate(LocalDate startDate, LocalDate endDate) {
        return fileName -> {
            String dateString = fileName.substring(DATE_SUBSTRING_BEGIN_INDEX);
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN_IN_FILENAME));
            return !date.isBefore(startDate) && !date.isAfter(endDate);
        };
    }
}
