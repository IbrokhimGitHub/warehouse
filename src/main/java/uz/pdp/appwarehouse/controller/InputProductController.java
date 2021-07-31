package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.InputProductResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;

@RestController
@RequestMapping("inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping("add")
    public Result add(@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.add(inputProductDto);
        return result;
    }
    @GetMapping("getAll")
    public Page<InputProduct> getByPage(@RequestParam int page){
        Page<InputProduct> allByPage = inputProductService.getAllByPage(page);
        return allByPage;
    }
    @GetMapping("getById")
    public InputProductResult getById(@PathVariable Integer id){
        InputProductResult inputProductResult = inputProductService.getById(id);
        return inputProductResult;
    }
    @DeleteMapping
    public Result delete(){
        Result delete = inputProductService.delete();
        return delete;
    }
    @PutMapping("edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        Result edit = inputProductService.edit(id, inputProductDto);
        return edit;
    }

}
