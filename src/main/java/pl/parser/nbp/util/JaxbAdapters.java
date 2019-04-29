package pl.parser.nbp.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

public class JaxbAdapters {
    public static class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
        public LocalDate unmarshal(String dateString) {
            return LocalDate.parse(dateString);
        }

        public String marshal(LocalDate localDate) {
            return localDate.toString();
        }
    }

    public static class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {
        private static final String NBP_DECIMAL_DELIMITER = ",";
        private static final String DECIMAL_DELIMITER = ".";

        public BigDecimal unmarshal(String numberString) {
            String numberWithFixedDelimiter = numberString.replace(NBP_DECIMAL_DELIMITER, DECIMAL_DELIMITER);
            return new BigDecimal(numberWithFixedDelimiter);
        }

        public String marshal(BigDecimal number) {
            return number.toString();
        }
    }
}
