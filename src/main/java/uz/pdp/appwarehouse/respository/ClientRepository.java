package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
