package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.respository.OutputRepository;
import uz.pdp.appwarehouse.result.OutputResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.service.OutputService;

@RestController
@RequestMapping
public class OutputController {
    @Autowired
    OutputService outputService;
    @PostMapping("add")
    public Result add(@RequestBody OutputDto outputDto){
        Result add = outputService.add(outputDto);
        return add;
    }
    @GetMapping("getByPage")
    public Page<Output> getByPage(@RequestParam int page){
        Page<Output> all = outputService.getAll(page);
        return all;
    }
    @GetMapping("getById/{id}")
    public OutputResult getById(@PathVariable Integer id){
        OutputResult byId = outputService.getById(id);
        return byId;
    }
    @PutMapping("edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        Result edit = outputService.edit(outputDto, id);
        return edit;
    }
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        Result delete = outputService.delete();
        return delete;
    }


}
