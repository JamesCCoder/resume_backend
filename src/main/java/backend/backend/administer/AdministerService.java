package backend.backend.administer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministerService {
    @Autowired
    private AdministerRepository repository;

    public boolean validateUser(String username, String password) {
        Administer administer = repository.findByUsername(username);
        return administer != null && administer.getPassword().equals(password);
    }
}
