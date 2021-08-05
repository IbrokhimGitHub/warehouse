package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.InputsForDashboard;

public interface InputRepository extends JpaRepository<Input,Integer> {

}
