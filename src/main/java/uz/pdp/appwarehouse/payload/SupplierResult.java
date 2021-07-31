package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResult {
    private Supplier supplier;
    private boolean isSupplier;
}

