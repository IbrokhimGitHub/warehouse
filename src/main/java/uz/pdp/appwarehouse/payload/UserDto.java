package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Set<Integer> warehousesId;
    private String newPhoneNumber;
    private String newPassword;


}
