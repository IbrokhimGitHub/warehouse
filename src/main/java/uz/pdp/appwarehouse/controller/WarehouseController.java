package uz.pdp.appwarehouse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;


import uz.pdp.appwarehouse.result.WarehouseResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.service.WarehouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;
    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        Result result = warehouseService.addWarehouseService(warehouse);
        return  result;
    }
    @GetMapping("getById/{id}")
    public WarehouseResult getById(@PathVariable Integer id){
        WarehouseResult warehouseResult = warehouseService.getWarehouse(id);
        return warehouseResult;
    }
    @GetMapping
    public List<Warehouse> getAll(){
        List<Warehouse> currencies = warehouseService.getCurrencies();
        return currencies;
    }
    @DeleteMapping("deleted/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = warehouseService.deleteWarehouse(id);
        return result;
    }
    @PutMapping("editSupplier/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        Result result = warehouseService.editWarehouse(id, warehouse);
        return result;
    }
}

