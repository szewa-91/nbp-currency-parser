package pl.parser.nbp.snapshot.provider;

import pl.parser.nbp.snapshot.CurrenciesSnapshotResponse;

import javax.xml.bind.JAXBException;

public interface CurrenciesSnapshotProvider {
    CurrenciesSnapshotResponse getCurrencies(String urlName) throws JAXBException;

}
