package keleshteri.clinic.management.auth.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {

    private String username;

    @NotNull(message = "Login password cannot be blank")
    private String password;

//    private DeviceInfo deviceInfo;

}
