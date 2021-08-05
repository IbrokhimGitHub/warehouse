package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredProductsForDashboard {
    private Integer expiredCount;
    private String productName;
    private Double price;
    private Double amount;
    private String measurement;
}
