package info.katikireddy622.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);

//    @Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
//    @CircuitBreaker(name = "default",fallbackMethod = "hardCodedResponse")

    @GetMapping("/sample-api")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "sample-api")
    public String sampleAPI()
    {
        logger.info("Entered into Sample API Method");
        return "Hey Rahul";
    }
    public String hardCodedResponse(Exception ex)
    {
        return "fallback-response";
    }
}
