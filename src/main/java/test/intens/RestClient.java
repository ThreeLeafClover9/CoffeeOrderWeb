package test.intens;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        UriComponents uriComponents =
                UriComponentsBuilder
                        .newInstance()
                        .scheme("http")
                        .host("worldtimeapi.org")
                        .port(80)
                        .path("/api/timezone/{continents}/{city}")
                        .encode()
                        .build();
        URI uri = uriComponents.expand("Asia", "Seoul").toUri();

        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

        WorldTime worldTime = restTemplate.getForObject(uri, WorldTime.class);
        System.out.println(worldTime);

        ResponseEntity<WorldTime> responseEntity = restTemplate.getForEntity(uri, WorldTime.class);
        WorldTime body = responseEntity.getBody();
        System.out.println(body);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println(statusCodeValue);
        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println(headers);

        ResponseEntity<WorldTime> exchange = restTemplate.exchange(uri, HttpMethod.GET, null, WorldTime.class);
        System.out.println(exchange);
    }
}
