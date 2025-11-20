package levelUp_backEnd.levelup.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import levelUp_backEnd.levelup.Dto.Dtos.LoginRequest;
import levelUp_backEnd.levelup.Dto.Dtos.RegisterRequest;
import levelUp_backEnd.levelup.Dto.Dtos.UserResponse;
import levelUp_backEnd.levelup.Model.User;
import levelUp_backEnd.levelup.Service.UserService;
import levelUp_backEnd.levelup.Web.Mappers;

@RestController
// NOTA IMPORTANTE: Quitamos el @RequestMapping("/api/auth") global para poder manejar la ruta raíz "/"
// Las rutas específicas se ajustarán abajo.
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // --- Endpoint de Salud para Google Load Balancer ---
    // Responde en la raíz del puerto 8081 (http://ip:8081/)
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK - Auth Service is Healthy");
    }

    // --- Endpoints de Autenticación ---
    // Añadimos el prefijo "/api/auth" aquí manualmente

    @PostMapping("/api/auth/login")
    public UserResponse login(@Validated @RequestBody LoginRequest req) {
        User u = userService.authenticate(req.email, req.password);
        return Mappers.toUserResponse(u);
    }

    @PostMapping("/api/auth/register")
    public UserResponse register(@Validated @RequestBody RegisterRequest req) {
        User u = userService.register(req.name, req.email, req.password);
        return Mappers.toUserResponse(u);
    }
}