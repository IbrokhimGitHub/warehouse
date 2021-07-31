package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.respository.UserRepository;
import uz.pdp.appwarehouse.respository.WarehouseRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;
    public Result add(UserDto userDto) {

    }
}
