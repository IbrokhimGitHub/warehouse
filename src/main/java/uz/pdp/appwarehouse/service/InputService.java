package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.respository.CurrencyRepository;
import uz.pdp.appwarehouse.respository.InputRepository;
import uz.pdp.appwarehouse.respository.SupplierRepository;
import uz.pdp.appwarehouse.respository.WarehouseRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class InputService {
    private static Integer factureNumber=0;

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result add(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Cant find such warehouse",false);
        }
        if (!optionalWarehouse.get().isActive()) {
            return new Result("Warehouse right now deactivated",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Cant find such supplier",false);
        }
        if (!optionalSupplier.get().isActive()) {
            return new Result("Supplier right now deactivated",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Cant find such currency",false);
        }
        if (!optionalCurrency.get().isActive()) {
            return new Result("Currency right now deactivated",false);
        }


        Input input =new Input();
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        Calendar calendar=Calendar.getInstance();
        input.setDate(new Timestamp(calendar.getTimeInMillis()));
        factureNumber++;
        input.setFactureNumber(factureNumber.toString());
        String uniqueCode = UUID.randomUUID().toString();
        input.setCode(uniqueCode);
        inputRepository.save(input);
        return new Result("Input saved successfully",true);





    }
    public Page<Input> getAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Input> all = inputRepository.findAll(pageable);
        return all;
    }
    public InputResult getById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            return new InputResult(optionalInput.get(),true);
        }
      return new InputResult(new Input(),false);
    }
    public Result edit(InputDto inputDto,Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("cant find such input",false);
        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Cant find such warehouse",false);
        }
        if (!optionalWarehouse.get().isActive()) {
            return new Result("Warehouse right now deactivated",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Cant find such supplier",false);
        }
        if (!optionalSupplier.get().isActive()) {
            return new Result("Supplier right now deactivated",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Cant find such currency",false);
        }
        if (!optionalCurrency.get().isActive()) {
            return new Result("Currency right now deactivated",false);
        }
        Input input = optionalInput.get();
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new Result("input with id: "+input.getId()+" edited successfully",true);

    }
    public Result delete(){
        return new Result("You cannot delete any inputs",false);
    }
}
