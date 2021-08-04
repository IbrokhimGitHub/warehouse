package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
}
