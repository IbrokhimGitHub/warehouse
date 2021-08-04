package uz.pdp.appwarehouse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;


import uz.pdp.appwarehouse.result.CurrencyResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.service.CurrencyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        Result result = currencyService.addCurrencyService(currency);
        return  result;
    }
    @GetMapping("getById/{id}")
    public CurrencyResult getById(@PathVariable Integer id){
        CurrencyResult currencyResult = currencyService.getCurrency(id);
        return currencyResult;
    }
    @GetMapping
    public List<Currency> getAll(){
        List<Currency> currencies = currencyService.getCurrencies();
        return currencies;
    }
    @DeleteMapping("deleted/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = currencyService.deleteCurrency(id);
        return result;
    }
    @PutMapping("editSupplier/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Currency currency){
        Result result = currencyService.editCurrency(id, currency);
        return result;
    }
}
