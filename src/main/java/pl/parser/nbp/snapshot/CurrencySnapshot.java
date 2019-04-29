package pl.parser.nbp.snapshot;

import pl.parser.nbp.util.JaxbAdapters.BigDecimalAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencySnapshot {
    @XmlElement(name = "nazwa_waluty")
    private String currencyName;
    @XmlElement(name = "przelicznik")
    private BigDecimal factor;
    @XmlElement(name = "kod_waluty")
    private String currencyCode;
    @XmlJavaTypeAdapter(value = BigDecimalAdapter.class)
    @XmlElement(name = "kurs_kupna")
    private BigDecimal buyRate;
    @XmlJavaTypeAdapter(value = BigDecimalAdapter.class)
    @XmlElement(name = "kurs_sprzedazy")
    private BigDecimal sellRate;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }
}
