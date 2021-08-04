package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Input;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputResult {
    private Input input;
    private  boolean isInput;
}
