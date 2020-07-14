package com.smalik.resilience.frontend;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Profile({ "frontend", "cb-frontend" })
@RestController
public class FrontendController {

    private BackendService service;

    public FrontendController(BackendService service) {
        this.service = service;
    }

    @GetMapping("/frontend/wait/{maxWaitMillis}")
    public ResponseEntity<FrontendResponse> getResponse(@PathVariable("maxWaitMillis") int maxWaitMillis) throws Exception {
        Optional<FrontendResponse> response = service.getResponse(maxWaitMillis);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            return ResponseEntity.status(503).build();
        }
    }
}
