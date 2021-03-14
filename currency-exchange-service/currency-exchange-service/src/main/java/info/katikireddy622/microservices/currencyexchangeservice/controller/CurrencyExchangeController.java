package info.katikireddy622.microservices.currencyexchangeservice.controller;

import info.katikireddy622.microservices.currencyexchangeservice.model.CurrencyExchange;
import info.katikireddy622.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/from/{currency1}/to/{currency2}")
    public CurrencyExchange getExchangeRate(@PathVariable("currency1") String from,@PathVariable("currency2") String to)
    {
        CurrencyExchange currencyExchange= currencyExchangeRepository.findFirstByFromAndTo(from,to);
        if(currencyExchange==null)
        {
            throw new RuntimeException("unable to find data for "+from+" to "+to);
        }
        currencyExchange.setEnvironment(environment.getProperty("server.port"));
        return currencyExchange;
    }

}
