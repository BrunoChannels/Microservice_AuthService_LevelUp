
package levelUp_backEnd.levelup.Controller;

import levelUp_backEnd.levelup.Model.User;
import levelUp_backEnd.levelup.Service.UserService;
import levelUp_backEnd.levelup.Web.Mappers;
import levelUp_backEnd.levelup.Dto.Dtos.LoginRequest;
import levelUp_backEnd.levelup.Dto.Dtos.RegisterRequest;
import levelUp_backEnd.levelup.Dto.Dtos.UserResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@Validated @RequestBody LoginRequest req) {
        User u = userService.authenticate(req.email, req.password);
        return Mappers.toUserResponse(u);
    }

    @PostMapping("/register")
    public UserResponse register(@Validated @RequestBody RegisterRequest req) {
        User u = userService.register(req.name, req.email, req.password);
        return Mappers.toUserResponse(u);
    }
}
