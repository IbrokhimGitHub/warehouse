package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Output;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputResult {
    private Output output;
    private boolean isOutput;
}
