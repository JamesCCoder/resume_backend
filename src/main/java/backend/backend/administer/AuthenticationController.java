package backend.backend.administer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "https://www.jamesresume.online",
        "http://localhost:3000",
        "https://james-resume-av9x.vercel.app/",
        "https://james-resume-backend-9a3094b7738e.herokuapp.com"
})
public class AuthenticationController {
    @Autowired
    private AdministerService administerService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Administer administer) {
        if (administerService.findByUsername(administer.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        administerService.save(administer);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Administer administer) {
        boolean isValidUser = administerService.validateUser(administer.getUsername(), administer.getPassword());
        if (!isValidUser) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }
        return ResponseEntity.ok("Login successful");
    }

}
