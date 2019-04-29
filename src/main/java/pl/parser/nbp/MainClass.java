package pl.parser.nbp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;

import java.time.LocalDate;
import java.util.List;

public class MainClass {
    private static final LocalDate START_DATE = LocalDate.of(2007, 4, 13);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        CurrenciesSnapshotService currenciesSnapshotService = context.getBean(CurrenciesSnapshotService.class);
        List<CurrenciesSnapshotResponse> snapshotFromUrl =
                currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, START_DATE);
        System.out.println(snapshotFromUrl);
    }
}
