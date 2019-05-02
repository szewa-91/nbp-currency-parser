package pl.parser.nbp.statistics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pl.parser.nbp.currency.CurrencyStatistics;
import pl.parser.nbp.snapshot.CurrencySnapshot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class StatisticsServiceImplTest {
    private StatisticsService statisticsService = new StatisticsServiceImpl();

    @Parameters(name = "{index}: average: {0}, deviation={1}")
    public static Iterable<Object[]> data() {
        return asList(new Object[][]{
                {"4.6333", "0.1247", asList(
                        currencySnapshot("4.4", "4.7"),
                        currencySnapshot("4.6", "4.8"),
                        currencySnapshot("4.9", "5.0"))},
                {null, null, emptyList()},
                {"4.4", "0", asList(
                        currencySnapshot("4.4", "4.5"),
                        currencySnapshot("4.4", "4.5"),
                        currencySnapshot("4.4", "4.5"),
                        currencySnapshot("4.4", "4.5"))},
                {"2.1308", "0.0117", asList(
                        currencySnapshot("2.1244", "2.2244"),
                        currencySnapshot("2.1465", "2.2465"),
                        currencySnapshot("2.1364", "2.2364"),
                        currencySnapshot("2.1157", "2.2157"))
                        },
                {"4.1505", "0.0125", asList(
                        currencySnapshot("4.1301", "4.2135"),
                        currencySnapshot("4.1621", "4.2461"),
                        currencySnapshot("4.1530", "4.2370"),
                        currencySnapshot("4.1569", "4.2409"))
                },
        });
    }

    public StatisticsServiceImplTest(String buyAverage, String sellDeviation, Collection<CurrencySnapshot> currencySnapshots) {
        this.buyMean = buyAverage == null
                ? null
                : new BigDecimal(buyAverage).setScale(4, RoundingMode.HALF_EVEN);
        this.sellDeviation = sellDeviation == null
                ? null
                : new BigDecimal(sellDeviation).setScale(4, RoundingMode.HALF_EVEN);
        this.currencySnapshots = currencySnapshots;
    }

    private BigDecimal buyMean;
    private BigDecimal sellDeviation;
    private Collection<CurrencySnapshot> currencySnapshots;

    @Test
    public void shouldCalculateStatistics() {
        CurrencyStatistics currencyStatistics =
                statisticsService.calculateStatistics(currencySnapshots);

        assertThat(currencyStatistics.getAverageBuyRate())
                .isEqualTo(buyMean);
        assertThat(currencyStatistics.getSellRateStandardDeviation())
                .isEqualTo(sellDeviation);

    }

    private static CurrencySnapshot currencySnapshot(String buyRate, String sellRate) {
        CurrencySnapshot currencySnapshot = new CurrencySnapshot();
        currencySnapshot.setCurrencyCode("USD");
        currencySnapshot.setBuyRate(new BigDecimal(buyRate));
        currencySnapshot.setSellRate(new BigDecimal(sellRate));
        return currencySnapshot;
    }
}