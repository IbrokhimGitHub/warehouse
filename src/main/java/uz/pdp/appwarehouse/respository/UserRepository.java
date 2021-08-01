package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
