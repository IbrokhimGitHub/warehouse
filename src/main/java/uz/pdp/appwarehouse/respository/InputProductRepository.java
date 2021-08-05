package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.ExpiredProductsForDashboard;
import uz.pdp.appwarehouse.payload.InputsForDashboard;

import java.util.Date;
import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    @Query(value = "select  pr.id, pr.name, sum(ip.amount)as amount, sum(ip.price) price, count(pr.id) from input_product ip \n" +
            "join product pr on ip.product_id = pr.id join input i on i.id = ip.input_id  where i.date=now() \n" +
            "group by pr.id",nativeQuery = true)
    List<InputsForDashboard> getDistinctByDate();

    @Query(value = "select  count(p.id) as expired_count, p.name as product_name, sum(ip.price) price,sum(ip.amount) amount, m.name as measurement from  input_product ip\n" +
            "    join product p on p.id = ip.product_id\n" +
            "join measurement m on p.measurement_id = m.id\n" +
            "where  expire_date<now()\n" +
            "group by p.name, m.name",nativeQuery = true)
    List<ExpiredProductsForDashboard> getExpired();



}
