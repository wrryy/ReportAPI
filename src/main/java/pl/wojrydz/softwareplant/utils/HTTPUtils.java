package pl.wojrydz.softwareplant.utils;

import org.springframework.web.client.RestTemplate;

public interface HTTPUtils {

    RestTemplate restTemplate = new RestTemplate();

    public default <T> T callForPage(String swapiUrl, Class<T> clazz) {
        T responseEntity = restTemplate.getForObject(swapiUrl, clazz);
        return responseEntity;
    }

}
