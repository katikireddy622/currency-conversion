package info.katikireddy622.microservices.currencyconversionservice.proxy;

import info.katikireddy622.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "currency-exchange-service",url = "localhost:8000")
@FeignClient(name ="currency-exchange-service")
@RequestMapping("/currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/from/{currency1}/to/{currency2}")
    public CurrencyConversion getExchangeRate(@PathVariable("currency1") String from, @PathVariable("currency2") String to);

}
