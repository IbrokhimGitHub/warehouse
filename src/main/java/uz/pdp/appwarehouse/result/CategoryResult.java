package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResult {
    public Category category;
    private boolean isCategory=false;
}
