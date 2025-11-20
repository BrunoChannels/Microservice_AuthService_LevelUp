package levelUp_backEnd.levelup.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    // Este endpoint responde en la raíz "/"
    // Google Load Balancer llamará aquí para saber si el pod está vivo.
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK - Auth Service is Healthy");
    }
}