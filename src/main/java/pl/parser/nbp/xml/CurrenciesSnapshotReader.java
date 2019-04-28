package pl.parser.nbp.xml;

import org.springframework.web.client.RestTemplate;
import pl.parser.nbp.model.CurrenciesSnapshotResponse;

public class CurrenciesSnapshotReader {
    private RestTemplate restTemplate;

    public CurrenciesSnapshotReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public CurrenciesSnapshotResponse getSnapshotFromUrl(String urlName) {
        return restTemplate.getForObject(urlName, CurrenciesSnapshotResponse.class);
    }
}
