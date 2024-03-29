package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.InputProduct;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductResult {
    private InputProduct inputProduct;
    private boolean isInputProduct;
}
