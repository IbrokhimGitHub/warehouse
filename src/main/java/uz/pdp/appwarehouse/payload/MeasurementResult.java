package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class MeasurementResult {
    public Measurement measurement;
    private boolean isMeasurement=false;

}
