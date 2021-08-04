package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;


import uz.pdp.appwarehouse.payload.SupplierResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.SupplierRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplierService(Supplier supplier){
        boolean existsByName = supplierRepository.existsByName(supplier.getName());
        if (existsByName)
            return new Result("such supplier is exist",false);
        supplierRepository.save(supplier);
        return new Result("saved successfully",true);

    }
    public List<Supplier> getCurrencies(){
        List<Supplier> currencies = supplierRepository.findAll();
        return currencies;
    }
    public SupplierResult getSupplier(Integer id){
        Optional<Supplier> optionalsupplier = supplierRepository.findById(id);
        if (optionalsupplier.isPresent()) {
            return new SupplierResult(optionalsupplier.get(),true);
        }else {
            return new SupplierResult(new Supplier(),false);
        }
    }

    public Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setActive(false);
            return new Result("supplier deactivated",true);
        }
        else
            return new Result("cant find such supplier",false);
    }
    public Result editSupplier(Integer id, Supplier supplierIntro){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("cant find such supplier",false);
        }
        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierIntro.getName());
        supplier.setPhoneNumber(supplierIntro.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("supplier edited successfully",true);

    }

}

