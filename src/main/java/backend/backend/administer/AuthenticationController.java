package backend.backend.administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
        }
        administerService.save(administer);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Administer administer) {
        boolean isValidUser = administerService.validateUser(administer.getUsername(), administer.getPassword());
        if (!isValidUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password!");
        }
//        if (isValidUser) {
//            return ResponseEntity.ok("Login successful");
//        }
        return ResponseEntity.ok("Login successful");
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/register")
    public ResponseEntity<?> handleOptionsRegister() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                .build();
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/login")
    public ResponseEntity<?> handleOptionsLogin() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                .build();
    }
}
