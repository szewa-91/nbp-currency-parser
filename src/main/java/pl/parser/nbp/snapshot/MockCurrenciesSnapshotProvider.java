package pl.parser.nbp.snapshot;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MockCurrenciesSnapshotProvider implements CurrenciesSnapshotProvider {
    private static final String FILE_PATH = "src/test/resources/%s.xml";

    @Override
    public CurrenciesSnapshotResponse getCurrencies(String fileName) {
        try {
            File reader = new File(String.format(FILE_PATH, fileName));
            return (CurrenciesSnapshotResponse) CurrenciesSnapshotResponse.getUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

}
