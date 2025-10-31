
package levelUp_backEnd.levelup.Dto;

import levelUp_backEnd.levelup.Model.Preferences;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Dtos {

    public static class LoginRequest {
        @Email @NotBlank
        public String email;
        @NotBlank
        public String password;
    }

    public static class RegisterRequest {
        @NotBlank public String name;
        @Email @NotBlank public String email;
        @NotBlank public String password;
    }

    public static class UpdateProfileRequest {
        public String name;
        public String address;
        public String phone;
        public Preferences preferences;
    }

    public static class UserResponse {
        public Long id;
        public String name;
        public String email;
        public String address;
        public String phone;
        public Preferences preferences;
    }
}
