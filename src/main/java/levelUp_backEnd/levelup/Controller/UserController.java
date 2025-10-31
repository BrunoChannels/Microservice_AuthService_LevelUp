
package levelUp_backEnd.levelup.Controller;

import levelUp_backEnd.levelup.Model.User;
import levelUp_backEnd.levelup.Service.UserService;
import levelUp_backEnd.levelup.Web.Mappers;  
import levelUp_backEnd.levelup.Dto.Dtos;
import levelUp_backEnd.levelup.Dto.Dtos.UserResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // En un sistema real, se usaría el usuario autenticado.
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        User u = userService.get(id);
        return Mappers.toUserResponse(u);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Validated @RequestBody Dtos.UpdateProfileRequest req) {
        User u = userService.update(id, req);
        return Mappers.toUserResponse(u);
    }
}
