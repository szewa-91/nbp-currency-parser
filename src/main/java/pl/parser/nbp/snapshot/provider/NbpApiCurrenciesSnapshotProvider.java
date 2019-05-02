package pl.parser.nbp.snapshot.provider;

import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.net.URL;

public class NbpApiCurrenciesSnapshotProvider implements CurrenciesSnapshotProvider {
    private static final String NBP_URL = "http://www.nbp.pl/kursy/xml/%s.xml";

    @Override
    public CurrenciesSnapshotResponse getCurrencies(String file) throws JAXBException {
        try {
            URL url = new URL(String.format(NBP_URL, file));
            return (CurrenciesSnapshotResponse) CurrenciesSnapshotResponse.getUnmarshaller().unmarshal(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
