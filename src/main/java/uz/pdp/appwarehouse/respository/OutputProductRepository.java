package uz.pdp.appwarehouse.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputsForDashboard;

import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
@Query(value = "select op.product_id, count(op.product_id) as sold_number,sum(op.amount) as sold_amount ,sum(op.price) as price,m.name as measurement from output_product op\n" +
        "join product p on op.product_id = p.id\n" +
        "join  measurement m on p.measurement_id = m.id\n" +
        "join output o on op.output_id = o.id\n" +
        "where o.date=now()\n" +
        "\n" +
        "group by op.product_id,m.name\n" +
        "order by count(op.product_id) desc\n" +
        "limit 10",nativeQuery = true)
    List<OutputsForDashboard> outputsForDashboard();
}
