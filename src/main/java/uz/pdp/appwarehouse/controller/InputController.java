package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;



@RestController
@RequestMapping("input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping("add")
    public Result add(@RequestBody InputDto inputDto){
        Result add = inputService.add(inputDto);
        return add;
    }
    @GetMapping("getAll")
    public Page<Input> getInputs(@RequestParam int page){
        Page<Input> inputPage = inputService.getAll(page);
        return inputPage;
    }
    @GetMapping("getOne")
    public InputResult getById(@PathVariable Integer id){
        InputResult inputResult = inputService.getById(id);
        return inputResult;
    }
    @PutMapping("edit")
    public Result edit(@PathVariable Integer id,@RequestBody InputDto inputDto){
        Result edit = inputService.edit(inputDto, id);
        return edit;
    }

    @DeleteMapping("delete")
    public Result tryToDelete(){
        Result delete = inputService.delete();
        return delete;
    }


}
