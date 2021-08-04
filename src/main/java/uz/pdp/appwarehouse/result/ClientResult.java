package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import uz.pdp.appwarehouse.entity.Client;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResult {
    private Client client;
    private boolean isClient;
}
