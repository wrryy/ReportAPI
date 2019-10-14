package pl.wojrydz.softwareplant.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pl.wojrydz.softwareplant.api.model.SingleResource;

public class Utils {

    private static RestTemplate restTemplate = new RestTemplate();

    public static <T> T callForPage(String swapiUrl, Class<T> clazz) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(swapiUrl, clazz);
        return responseEntity.getBody();
    }

//    public static <T extends SingleResource> void parseAndSetId(T resource){
//        String resourceUrl = resource.getUrl();
//        resource.setPeople_id(resourceUrl.split("/")[5]);
//    }

//    public static <T extends SingleResource, S extends Page> Optional<T> findMatch(S page, String query_criteria) {
//        return page.getChildren().stream()
//                .filter(resource -> query_criteria.equals(resource.getName()))
//                .findFirst();
//    }

}
