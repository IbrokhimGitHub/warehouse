package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InputsForDashboard {
    private Integer id;
    private String name;
    private Double amount;
    private Double price;
    private Integer count;
}
