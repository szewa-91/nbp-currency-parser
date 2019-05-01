package pl.parser.nbp;

import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;
import pl.parser.nbp.snapshot.CurrenciesSnapshotService;
import pl.parser.nbp.snapshot.CurrencySnapshotContext;

import java.time.LocalDate;
import java.util.List;

public class MainClass {
    private static final LocalDate START_DATE = LocalDate.of(2019, 2, 10);
    private static final LocalDate END_DATE = LocalDate.of(2019, 2, 20);

    public static void main(String[] args) {
        CurrencySnapshotContext context = new CurrencySnapshotContext();
        List<CurrenciesSnapshotResponse> currenciesSnapshots =
                context.currenciesSnapshotService().getCurrenciesSnapshots(START_DATE, END_DATE);
        System.out.println(currenciesSnapshots);
    }
}
