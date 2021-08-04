package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.result.OutputResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.CurrencyRepository;
import uz.pdp.appwarehouse.respository.OutputRepository;
import uz.pdp.appwarehouse.respository.ClientRepository;
import uz.pdp.appwarehouse.respository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OutputService {
    private static Integer factureNumber=0;

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Cant find such warehouse",false);
        }
        if (!optionalWarehouse.get().isActive()) {
            return new Result("Warehouse right now deactivated",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Cant find such client",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Cant find such currency",false);
        }
        if (!optionalCurrency.get().isActive()) {
            return new Result("Currency right now deactivated",false);
        }


        Output output =new Output();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        Calendar calendar=Calendar.getInstance();
        output.setDate(new Timestamp(calendar.getTimeInMillis()));
        factureNumber++;
        output.setFactureNumber(factureNumber.toString());
        String uniqueCode = UUID.randomUUID().toString();
        output.setCode(uniqueCode);
        outputRepository.save(output);
        return new Result("Output saved successfully",true);





    }
    public Page<Output> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Output> all = outputRepository.findAll(pageable);
        return all;
    }
    public OutputResult getById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            return new OutputResult(optionalOutput.get(),true);
        }
        return new OutputResult(new Output(),false);
    }
    public Result edit(OutputDto outputDto,Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("cant find such output",false);
        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Cant find such warehouse",false);
        }
        if (!optionalWarehouse.get().isActive()) {
            return new Result("Warehouse right now deactivated",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Cant find such client",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Cant find such currency",false);
        }
        if (!optionalCurrency.get().isActive()) {
            return new Result("Currency right now deactivated",false);
        }
        Output output = optionalOutput.get();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        outputRepository.save(output);
        return new Result("output with id: "+output.getId()+" edited successfully",true);

    }
    public Result delete(){
        return new Result("You cannot delete any outputs",false);
    }
}

