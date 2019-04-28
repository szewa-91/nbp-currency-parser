package pl.parser.nbp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.model.CurrenciesSnapshotResponse;
import pl.parser.nbp.xml.CurrenciesSnapshotReader;

public class MainClass
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(NbpCurrencyParserConfig.class);
        CurrenciesSnapshotReader currenciesSnapshotReader = context.getBean(CurrenciesSnapshotReader.class);
        CurrenciesSnapshotResponse snapshotFromUrl = currenciesSnapshotReader.getSnapshotFromUrl("http://www.nbp.pl/kursy/xml/c073z070413.xml");
        System.out.println( snapshotFromUrl.getTableNumber() );
    }
}
