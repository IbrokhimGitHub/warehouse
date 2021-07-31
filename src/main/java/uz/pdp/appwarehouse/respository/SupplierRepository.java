package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    boolean existsByName(String name);
}
