package ajou.db.gpt.service;

import ajou.db.gpt.logger.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class HttpRequestService {

    private final RestTemplate restTemplate;

    /**
     * 외부 server에 HTTP request를 보낸다.
     *
     * @param requestUrl 요청을 보낼 외부 서버의 url
     * @param httpMethod 보낼 요청의 HTTP method
     * @param headers    header 정보
     * @return 요청에 대한 응답 데이터
     */
    public ResponseEntity<String> sendHttpRequest(String requestUrl, HttpMethod httpMethod, HttpHeaders headers, String body) {
        HttpEntity<String> request;
        if (body != null) {
            request = new HttpEntity<>(body, headers);
        } else {
            request = new HttpEntity<>(headers);
        }

        log.info("[{}] Send http request with uri={}, method={}, headers={}, body={}", LogUtils.getLogTraceId(), requestUrl, httpMethod, headers, body);

        return restTemplate.exchange(
                requestUrl,
                httpMethod,
                request,
                String.class
        );
    }
}
