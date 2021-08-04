package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.result.UserResult;
import uz.pdp.appwarehouse.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("add")
    public Result add(@RequestBody UserDto userDto){
        Result add = userService.add(userDto);
        return add;
    }
    @GetMapping("getByPage")
    public Page<User> getByPage(@RequestParam int page){
        Page<User> byPage = userService.getByPage(page);
        return byPage;
    }
    @GetMapping("getById/{id}")
    public UserResult getById(@PathVariable Integer id){
        UserResult byId = userService.getById(id);
        return byId;
    }
    @PutMapping("edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody UserDto userDto){
        Result edit = userService.edit(id, userDto);
        return edit;
    }
    @DeleteMapping("deactivate/{id}")
    public Result deactivate(@PathVariable Integer id){
        Result delete = userService.delete(id);
        return delete;
    }
}
