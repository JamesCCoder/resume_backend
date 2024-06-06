package backend.backend.administer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class AdministerService {
    @Autowired
    private AdministerRepository repository;
//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    String hashedPassword = passwordEncoder.encode("123456");

    public boolean validateUser(String username, String password) {
        Administer administer = repository.findByUsername(username);
        return administer != null && administer.getPassword().equals(password);
    }

//    public void saveAdminister(Administer administer) {
//        administer.setPassword(passwordEncoder.encode(administer.getPassword()));
//        repository.save(administer);
//    }
}
