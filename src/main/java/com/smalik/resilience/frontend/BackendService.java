package com.smalik.resilience.frontend;

import com.smalik.resilience.backend.BackendResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Profile("frontend")
@Service
public class BackendService {

    private RestTemplate template;

    public BackendService(RestTemplate template) {
        this.template = template;
    }

    public Optional<FrontendResponse> getResponse(int maxWaitMillis) throws Exception {
        long startTimeMillis = System.currentTimeMillis();
        ResponseEntity<BackendResponse> entity = template.getForEntity("/backend/wait/" + maxWaitMillis, BackendResponse.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            long totalTime = System.currentTimeMillis() - startTimeMillis;
            return Optional.of(new FrontendResponse(false, totalTime, entity.getBody()));
        }

        return Optional.empty();
    }
}