package id.ac.unnnur.logbook.security.service;

import id.ac.unnnur.logbook.exception.CommonException;
import id.ac.unnnur.logbook.model.User;
import id.ac.unnnur.logbook.payload.ErrorMessage;
import id.ac.unnnur.logbook.payload.ErrorSchema;
import id.ac.unnnur.logbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> {
            CommonException exception = new CommonException();
            ErrorSchema errorSchema = new ErrorSchema();
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setEnglish("Username not found");
            errorMessage.setBahasa("Username tidak ditemukan");
            errorSchema.setErrorCode("000000");
            errorSchema.setErrorMessage(errorMessage);
            exception.setErrorSchema(errorSchema);
            exception.setOutputSchema(null);
            throw exception;
        });

        return UserDetailsImplementation.build(user);
    }
}
