package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;


import uz.pdp.appwarehouse.result.WarehouseResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.WarehouseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouseService(Warehouse warehouse){
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("such warehouse is exist",false);
        warehouseRepository.save(warehouse);
        return new Result("saved successfully",true);

    }
    public List<Warehouse> getCurrencies(){
        List<Warehouse> currencies = warehouseRepository.findAll();
        return currencies;
    }
    public WarehouseResult getWarehouse(Integer id){
        Optional<Warehouse> optionalwarehouse = warehouseRepository.findById(id);
        if (optionalwarehouse.isPresent()) {
            return new WarehouseResult(optionalwarehouse.get(),true);
        }else {
            return new WarehouseResult(new Warehouse(),false);
        }
    }

    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            warehouse.setActive(false);
            return new Result("warehouse deactivated",true);
        }
        else
            return new Result("cant find such warehouse",false);
    }
    public Result editWarehouse(Integer id, Warehouse warehouseIntro){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new Result("cant find such warehouse",false);
        }
        Warehouse warehouse = optionalWarehouse.get();
        warehouse.setName(warehouseIntro.getName());
        warehouseRepository.save(warehouse);
        return new Result("warehouse edited successfully",true);

    }

}

