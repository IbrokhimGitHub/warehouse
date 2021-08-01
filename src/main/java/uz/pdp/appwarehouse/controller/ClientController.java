package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.ClientResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("add")
    public Result add(@RequestBody Client client){
        return clientService.add(client);
    }
    @GetMapping("getByPage")
    public Page<Client> clients(@RequestParam int page){
        return clientService.getByPage(page);
    }
    @GetMapping("getById/{id}")
    public ClientResult getById(@PathVariable Integer id){
        return clientService.getById(id);
    }
    @DeleteMapping("deactivate/{id}")
    public Result deactivate(@PathVariable Integer id){
        return clientService.delete(id);
    }
    @PutMapping("edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Client client){
        return clientService.edit(id,client);
    }

}
