package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
