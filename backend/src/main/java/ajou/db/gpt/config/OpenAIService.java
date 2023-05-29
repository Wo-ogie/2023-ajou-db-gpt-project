package ajou.db.gpt.config;

import ajou.db.gpt.dto.chat.GptRes;
import ajou.db.gpt.service.HttpRequestService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final HttpRequestService httpRequestService;
    private final Gson gson;

    public GptRes chat(String content) {
        String requestUri = UriComponentsBuilder
                .fromUriString("https://api.openai.com/v1/chat/completions")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        String body = "{" +
                "\"model\": \"gpt-3.5-turbo\", " +
                "\"messages\": [" +
                "{\"role\": \"user\", \"content\": \"" + content + "\"}" +
                "]" +
                "}";

        ResponseEntity<String> response;
        try {
            response = httpRequestService.sendHttpRequest(requestUri, HttpMethod.POST, headers, body);
        } catch (Exception ex) {
            throw new RuntimeException();
        }

        return gson.fromJson(response.getBody(), GptRes.class);
    }
}
