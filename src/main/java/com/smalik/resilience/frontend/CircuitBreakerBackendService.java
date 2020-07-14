package com.smalik.resilience.frontend;

import com.smalik.resilience.backend.BackendResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Profile("cb-frontend")
@Service
public class CircuitBreakerBackendService extends BackendService {

    public CircuitBreakerBackendService(RestTemplate template) {
        super(template);
    }

    @CircuitBreaker(name="backend", fallbackMethod="getFallbackResponse")
    public Optional<FrontendResponse> getResponse(int maxWaitMillis) throws Exception {
        return super.getResponse(maxWaitMillis);
    }

    public Optional<FrontendResponse> getFallbackResponse(int maxWaitMillis, Throwable ex) throws Exception {
        return Optional.of(new FrontendResponse(true, 0, new BackendResponse(0, new Date())));
    }
}