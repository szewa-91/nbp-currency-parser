package pl.parser.nbp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;

import java.time.LocalDate;
import java.util.List;

public class MainClass {
    private static final LocalDate START_DATE = LocalDate.of(2019, 2, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 2, 20);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        CurrenciesSnapshotService currenciesSnapshotService = context.getBean(CurrenciesSnapshotService.class);
        List<CurrenciesSnapshotResponse> currenciesSnapshots =
                currenciesSnapshotService.getCurrenciesSnapshots(START_DATE, END_DATE);
        System.out.println(currenciesSnapshots);
    }
}
