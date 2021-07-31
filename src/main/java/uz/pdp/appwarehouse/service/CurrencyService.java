package uz.pdp.appwarehouse.service;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;


import uz.pdp.appwarehouse.payload.CurrencyResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.respository.CurrencyRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency){
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("such currency is exist",false);
        currencyRepository.save(currency);
        return new Result("saved successfully",true);

    }
    public List<Currency> getCurrencies(){
        List<Currency> currencies = currencyRepository.findAll();
        return currencies;
    }
    public CurrencyResult getCurrency(Integer id){
        Optional<Currency> optionalcurrency = currencyRepository.findById(id);
        if (optionalcurrency.isPresent()) {
            return new CurrencyResult(optionalcurrency.get(),true);
        }else {
            return new CurrencyResult(new Currency(),false);
        }
    }

    public Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            currency.setActive(false);
            return new Result("currency deactivated",true);
        }
        else
            return new Result("cant find such currency",false);
    }
    public Result editCurrency(Integer id, Currency currencyIntro){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("cant find such currency",false);
        }
        Currency currency = optionalCurrency.get();
        currency.setName(currencyIntro.getName());
        currencyRepository.save(currency);
        return new Result("currency edited successfully",true);

    }

}

