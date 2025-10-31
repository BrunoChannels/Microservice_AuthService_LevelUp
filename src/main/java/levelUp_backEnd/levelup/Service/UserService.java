package levelUp_backEnd.levelup.Service;

import levelUp_backEnd.levelup.Model.User;
import levelUp_backEnd.levelup.Repository.UserRepository;
import levelUp_backEnd.levelup.Dto.Dtos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public User register(String name, String email, String password) {
        if (!hasText(name) || !hasText(email) || !hasText(password)) {
            throw new IllegalArgumentException("Nombre, email y contraseña son obligatorios");
        }
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        if (repo.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        User u = new User(name.trim(), normalizedEmail, password); // TODO: usar BCrypt en prod
        return repo.save(u);
    }

    @Transactional(readOnly = true)
    public User authenticate(String email, String password) {
        if (!hasText(email) || !hasText(password)) {
            throw new IllegalArgumentException("Email y contraseña son obligatorios");
        }
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        return repo.findByEmail(normalizedEmail)
                .filter(u -> Objects.equals(u.getPassword(), password))
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
    }

    @Transactional
    public User update(Long id, Dtos.UpdateProfileRequest req) {
        User u = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));

        if (req == null) throw new IllegalArgumentException("Request de actualización vacío");

        if (req.name != null && hasText(req.name)) u.setName(req.name.trim());
        if (req.address != null) u.setAddress(req.address.trim());
        if (req.phone != null) u.setPhone(req.phone.trim());
        if (req.preferences != null) u.setPreferences(req.preferences);

        return repo.save(u);
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
    }

    private boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
