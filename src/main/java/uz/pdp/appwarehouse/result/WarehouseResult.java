package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Warehouse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResult {
    private Warehouse warehouse;
    private boolean isWarehouse;
}

