package info.katikireddy622.microservices.currencyexchangeservice.repository;

import info.katikireddy622.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Integer> {

    CurrencyExchange findFirstByFromAndTo(String from,String to);

}
