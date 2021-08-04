package uz.pdp.appwarehouse.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;


import uz.pdp.appwarehouse.payload.SupplierResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.service.SupplierService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        Result result = supplierService.addSupplierService(supplier);
        return  result;
    }
    @GetMapping("getById/{id}")
    public SupplierResult getById(@PathVariable Integer id){
        SupplierResult supplierResult = supplierService.getSupplier(id);
        return supplierResult;
    }
    @GetMapping
    public List<Supplier> getAll(){
        List<Supplier> currencies = supplierService.getCurrencies();
        return currencies;
    }
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = supplierService.deleteSupplier(id);
        return result;
    }
    @PutMapping("editSupplier/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Supplier supplier){
        Result result = supplierService.editSupplier(id, supplier);
        return result;
    }
}

