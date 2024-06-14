package backend.backend.administer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;


@Service
public class AdministerService {
    @Autowired
    private AdministerRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean validateUser(String username, String password) {
        Administer administer = repository.findByUsername(username);
        return administer != null && bCryptPasswordEncoder.matches(password, administer.getPassword());
    }

    public Administer save(Administer administer) {
        administer.setPassword(bCryptPasswordEncoder.encode(administer.getPassword()));
        return repository.save(administer);
    }

    public Administer findByUsername(String username) {
        return repository.findByUsername(username);
    }
}