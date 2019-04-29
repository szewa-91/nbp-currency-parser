package pl.parser.nbp.snapshot;

import pl.parser.nbp.util.JaxbAdapters.LocalDateAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrenciesSnapshotResponse {
    @XmlElement(name = "numer_tabeli")
    private String tableNumber;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "data_publikacji")
    private LocalDate publicationDate;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "data_notowania")
    private LocalDate valueDate;

    @XmlElement(name = "pozycja")
    private List<CurrencySnapshot> positions;

    public CurrenciesSnapshotResponse() {
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public List<CurrencySnapshot> getPositions() {
        return positions;
    }

    public void setPositions(List<CurrencySnapshot> positions) {
        this.positions = positions;
    }

    public static Unmarshaller getUnmarshaller() throws JAXBException {
        return JAXBContext.newInstance(CurrenciesSnapshotResponse.class).createUnmarshaller();
    }
}