package com.smalik.resilience.backend;

import com.smalik.resilience.backend.BackendResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@Profile("backend")
@RestController
public class BackendController {

    private Random random = new Random();

    @GetMapping("/backend/wait/{maxWaitMillis}")
    public BackendResponse getValue(@PathVariable("maxWaitMillis") int maxWaitMillis) throws Exception {

        int waitTime = random.nextInt(maxWaitMillis);
        Thread.sleep(waitTime);

        return new BackendResponse(waitTime, new Date());
    }
}
