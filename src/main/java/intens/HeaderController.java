package intens;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/v1/headers")
public class HeaderController {
    @GetMapping("/1")
    public ResponseEntity getHeader(@RequestHeader("user-agent") String userAgent) {
        return new ResponseEntity<>(userAgent, HttpStatus.OK);
    }

    @GetMapping("/2")
    public ResponseEntity getHeader(@RequestHeader Map<String, String> headers) {
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/3")
    public ResponseEntity getHeader(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(httpServletRequest.getHeader("user-agent"),
                HttpStatus.OK);
    }

    @GetMapping("/4")
    public ResponseEntity getHeader(HttpEntity httpEntity) {
        return new ResponseEntity<>(httpEntity.getHeaders().getHost(), HttpStatus.OK);
    }

    @GetMapping("/5")
    public ResponseEntity responseHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-Geo-Location", "Korea, Seoul");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/6")
    public ResponseEntity responseHeader(HttpServletResponse response) {
        response.addHeader("Client-Geo-Location", "Korea, Seoul");
        return null;
    }
}
