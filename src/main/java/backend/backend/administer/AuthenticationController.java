package backend.backend.administer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "https://www.jamesresume.online",
        "http://localhost:3000",
        "https://james-resume-backend-9a3094b7738e.herokuapp.com"
})
public class AuthenticationController {
    @Autowired
    private AdministerService administerService;

    @PostMapping("/login")
    public boolean login(@RequestBody Administer administer) {
        return administerService.validateUser(administer.getUsername(), administer.getPassword());
    }
}
