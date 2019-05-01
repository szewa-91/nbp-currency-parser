package pl.parser.nbp.statistics;

public class StatisticsServiceFactory {
    public StatisticsService createStatisticsService() {
        return new StatisticsServiceImpl();
    }
}
