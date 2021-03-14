package info.katikireddy622.microservices.currencyconversionservice.controller;

import info.katikireddy622.microservices.currencyconversionservice.model.CurrencyConversion;
import info.katikireddy622.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/from/{currency1}/to/{currency2}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(@PathVariable("currency1") String from,@PathVariable("currency2") String to,@PathVariable("quantity") int quantity)
    {

        HashMap<String,String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        String URI="http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        CurrencyConversion currencyConversion= new RestTemplate().getForEntity(URI,CurrencyConversion.class,uriVariables).getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple()*quantity);
        currencyConversion.setEnvironment(environment.getProperty("server.port"));
        return currencyConversion;
    }


    @GetMapping("/feign/from/{currency1}/to/{currency2}/quantity/{quantity}")
    public CurrencyConversion getFeignCurrencyConversion(@PathVariable("currency1") String from,@PathVariable("currency2") String to,@PathVariable("quantity") int quantity)
    {
        CurrencyConversion currencyConversion=currencyExchangeProxy.getExchangeRate(from,to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple()*quantity);
        return currencyConversion;
    }


}
