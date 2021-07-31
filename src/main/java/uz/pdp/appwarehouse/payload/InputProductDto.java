package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {
    private Integer productId;
    private Integer inputId;
    private double amount;
    private double price;
    private String expireDate;

}
