package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputsForDashboard {
    private Integer productId;
    private Integer soldNumber;
    private Double soldAmount;
    private Double price;
    private String measurement;
}
