package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.payload.UserResult;
import uz.pdp.appwarehouse.respository.UserRepository;
import uz.pdp.appwarehouse.respository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    public Result add(UserDto userDto) {
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("user with such phone number already exist",false);
        }
        User user=new User();
        user.setActive(true);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        List<Warehouse> allById = warehouseRepository.findAllById(userDto.getWarehousesId());

        Set <Warehouse> warehouses=new HashSet<>(allById);

        user.setWarehouses(warehouses);
        String randomUUID = UUID.randomUUID().toString();
        user.setCode(randomUUID);

        userRepository.save(user);
        return new Result("new user saved",true);

    }
    public Result edit(Integer id,UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("user with such id not founded",false);
        }
        User user = optionalUser.get();
        if (!user.isActive()) {
            return new Result("user deactivated",false);
        }
        if (!user.getPhoneNumber().equals(userDto.getPhoneNumber())&&!user.getPassword().equals(userDto.getPassword())){
            return new Result("Phone number or/and password isn't exist",false);
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getNewPassword());
        user.setPhoneNumber(userDto.getNewPhoneNumber());
        List<Warehouse> allById = warehouseRepository.findAllById(userDto.getWarehousesId());
        Set<Warehouse> warehouses=new HashSet<>(allById);
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("User edited successfully",true);


    }
    public Page<User> getByPage(int page){
        Pageable pageable=PageRequest.of(page,10);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }
    public UserResult getById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new UserResult(new User(),false);
        }
        return new UserResult(optionalUser.get(),true);
    }
    public Result delete(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("there is no user",false);
        }
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return new Result("user deactivated",true);
    }
}
