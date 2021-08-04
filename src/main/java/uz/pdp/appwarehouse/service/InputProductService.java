package uz.pdp.appwarehouse.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.result.InputProductResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.InputProductRepository;
import uz.pdp.appwarehouse.respository.InputRepository;
import uz.pdp.appwarehouse.respository.ProductRepository;

import java.sql.Timestamp;
import java.util.Optional;


@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("cant find product",false);
        }
        if (!optionalProduct.get().isActive()) {
            return new Result("this product is deactivated",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("cant find product",false);
        }

        InputProduct inputProduct=new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        Timestamp expireDate = Timestamp.valueOf(inputProductDto.getExpireDate());
        inputProduct.setExpireDate(expireDate);
        InputProduct save = inputProductRepository.save(inputProduct);
        return new Result("inputProduct saved with id: "+save.getId(),true);


    }
    public Page<InputProduct> getAllByPage(Integer page){

        Pageable pageable = PageRequest.of(page, 10);
        Page<InputProduct> inputProducts = inputProductRepository.findAll(pageable);
        return inputProducts;
    }
    public InputProductResult getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new InputProductResult(new InputProduct(),false);
        }
        return new InputProductResult(optionalInputProduct.get(), true);
    }
    public Result edit(Integer id,InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("cant find product",false);
        }
        if (!optionalProduct.get().isActive()) {
            return new Result("this product is deactivated",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("cant find product",false);
        }
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("cant find such inputProduct",false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        Timestamp expireDate = Timestamp.valueOf(inputProductDto.getExpireDate());
        inputProduct.setExpireDate(expireDate);
        inputProductRepository.save(inputProduct);
        return new Result("input product edited",true);

    }
    public Result delete(){
        return new Result("you cant delete inputProduct",false);
    }
}
