package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResult {
    private Currency currency;
    private boolean isCurrency;
}
