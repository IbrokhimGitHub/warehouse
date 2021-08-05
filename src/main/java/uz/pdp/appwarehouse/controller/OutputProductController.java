package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.result.OutputProductResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

@RestController
@RequestMapping("outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;
    @PostMapping("add")
    public Result add(@RequestBody OutputProductDto outputProductDto){
        Result add = outputProductService.add(outputProductDto);
        return add;
    }
    @GetMapping("getById/{id}")
    public OutputProductResult getByID(@PathVariable Integer id)    {
        OutputProductResult byId = outputProductService.getById(id);
        return byId;
    }
    @GetMapping("getByPage")
    public Page<OutputProduct> getByPage(@RequestParam Integer page){
        Page<OutputProduct> allByPage = outputProductService.getAllByPage(page);
        return allByPage;
    }
    @PutMapping("edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        Result edit = outputProductService.edit(id, outputProductDto);
        return edit;
    }
    @DeleteMapping("delete/{id}")
    public Result delete(){
        Result delete = outputProductService.delete();
        return delete;
    }
}
