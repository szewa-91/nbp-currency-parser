package pl.parser.nbp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.NbpApiCurrenciesSnapshotProvider;

public class MainClass
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        NbpApiCurrenciesSnapshotProvider currenciesSnapshotReader = context.getBean(NbpApiCurrenciesSnapshotProvider.class);
        CurrenciesSnapshotResponse snapshotFromUrl = currenciesSnapshotReader.getCurrencies("http://www.nbp.pl/kursy/xml/c073z070413.xml");
        System.out.println( snapshotFromUrl.getTableNumber() );
    }
}
