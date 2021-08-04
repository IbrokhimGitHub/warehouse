package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.OutputProduct;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductResult {
    private OutputProduct outputProduct;
    private boolean isOutputProduct;
}
