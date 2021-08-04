package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.result.ClientResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result add(Client client){
        client.setActive(true);
        clientRepository.save(client);
        return new Result("new client saved",true);
    }
    public Result edit(Integer id,Client clientIntro){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("cant find such client",false);
        }
        if (optionalClient.get().isActive()) {
            return new Result("this client deactivated",false);
        }
        Client client = optionalClient.get();
        client.setPhoneNumber(clientIntro.getPhoneNumber());
        client.setName(clientIntro.getName());
        clientRepository.save(client);
        return new Result("Client edited",true);

    }
    public Page<Client> getByPage(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Client> all = clientRepository.findAll(pageable);
        return all;
    }
    public ClientResult getById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return new ClientResult(optionalClient.get(),true);
        }
        return new ClientResult(new Client(),false);
    }
    public Result delete(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("cant find such client",false);
        }
        Client client = optionalClient.get();
        client.setActive(false);
        clientRepository.save(client);
        return new Result("this client deactivated",true);

    }

}
