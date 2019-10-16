package pl.wojrydz.softwareplant.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Utils {

    private static RestTemplate restTemplate = new RestTemplate();

    public static <T> T callForPage(String swapiUrl, Class<T> clazz) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(swapiUrl, clazz);
        return responseEntity.getBody();
    }

}
