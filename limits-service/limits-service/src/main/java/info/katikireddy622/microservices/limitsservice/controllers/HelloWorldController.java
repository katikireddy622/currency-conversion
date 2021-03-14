package info.katikireddy622.microservices.limitsservice.controllers;

import info.katikireddy622.microservices.limitsservice.configuration.LimitServiceConfiguration;
import info.katikireddy622.microservices.limitsservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private LimitServiceConfiguration limitServiceConfiguration;

    @GetMapping("/hey/{name}")
    public String helloWorld(@PathVariable("name") String name)
    {
        return "Hello "+name;
    }

    @GetMapping("/getLimits")
    public Limits getLimits()
    {
        return new Limits(limitServiceConfiguration.getMinimum(),limitServiceConfiguration.getMaximum());
    }
}
