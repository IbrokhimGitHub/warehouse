package uz.pdp.appwarehouse.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.result.OutputProductResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.OutputProductRepository;
import uz.pdp.appwarehouse.respository.OutputRepository;
import uz.pdp.appwarehouse.respository.ProductRepository;
import java.util.Optional;


@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result add(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("cant find product",false);
        }
        if (!optionalProduct.get().isActive()) {
            return new Result("this product is deactivated",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("cant find product",false);
        }

        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());


        OutputProduct save = outputProductRepository.save(outputProduct);
        return new Result("outputProduct saved with id: "+save.getId(),true);


    }
    public Page<OutputProduct> getAllByPage(Integer page){

        Pageable pageable = PageRequest.of(page, 10);
        Page<OutputProduct> outputProducts = outputProductRepository.findAll(pageable);
        return outputProducts;
    }
    public OutputProductResult getById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new OutputProductResult(new OutputProduct(),false);
        }
        return new OutputProductResult(optionalOutputProduct.get(), true);
    }
    public Result edit(Integer id,OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("cant find product",false);
        }
        if (!optionalProduct.get().isActive()) {
            return new Result("this product is deactivated",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("cant find product",false);
        }
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new Result("cant find such outputProduct",false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("output product edited",true);

    }
    public Result delete(){
        return new Result("you cant delete outputProduct",false);
    }
}

