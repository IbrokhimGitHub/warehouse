package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);


}
