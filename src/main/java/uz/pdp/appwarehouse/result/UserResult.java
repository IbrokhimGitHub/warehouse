package uz.pdp.appwarehouse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.appwarehouse.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {
    private User user;
    private boolean isUser;
}
