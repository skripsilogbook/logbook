package id.ac.unnnur.logbook.service;

import id.ac.unnnur.logbook.exception.CommonException;
import id.ac.unnnur.logbook.model.Role;
import id.ac.unnnur.logbook.model.User;
import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.ErrorMessage;
import id.ac.unnnur.logbook.payload.ErrorSchema;
import id.ac.unnnur.logbook.payload.request.LoginRequest;
import id.ac.unnnur.logbook.payload.request.RegisterRequest;
import id.ac.unnnur.logbook.payload.response.JwtResponse;
import id.ac.unnnur.logbook.repository.RoleRepository;
import id.ac.unnnur.logbook.repository.UserRepository;
import id.ac.unnnur.logbook.security.jwt.JwtUtils;
import id.ac.unnnur.logbook.security.service.UserDetailsImplementation;
import id.ac.unnnur.logbook.util.RoleType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    public CommonResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());

        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role);


        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish("Success Login");
        errorMessage.setBahasa("Berhasil Masuk");
        errorSchema.setErrorCode("000000");
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(jwtResponse);
        response.setErrorSchema(errorSchema);
        return response;
    }

    public CommonResponse register(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            CommonException exception = new CommonException();
            ErrorSchema errorSchema = new ErrorSchema();
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setEnglish("Username is already taken");
            errorMessage.setBahasa("Username sudah digunakan");
            errorSchema.setErrorCode("000000");
            errorSchema.setErrorMessage(errorMessage);
            exception.setErrorSchema(errorSchema);
            exception.setOutputSchema(null);
            throw exception;
        }

        if (repository.existsByEmail(request.getEmail())) {
            CommonException exception = new CommonException();
            ErrorSchema errorSchema = new ErrorSchema();
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setEnglish("Email is already taken");
            errorMessage.setBahasa("Email sudah digunakan");
            errorSchema.setErrorCode("000000");
            errorSchema.setErrorMessage(errorMessage);
            exception.setErrorSchema(errorSchema);
            exception.setOutputSchema(null);
            throw exception;
        }

        // Create new user's account
        User user = new User(request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getEmail());

        String role = request.getRole();


        if (role == null) {
            Role userRole = roleRepository.findByRoleName(RoleType.ROLE_KARYAWAN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            switch (role) {
                case "karyawan":
                    Role karyawanRole = roleRepository.findByRoleName(RoleType.ROLE_KARYAWAN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(karyawanRole);
                    break;
                case "admin":
                    Role adminRole = roleRepository.findByRoleName(RoleType.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(adminRole);
            }
        }

        repository.save(user);

        ErrorSchema errorSchema = new ErrorSchema();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setEnglish("Success Register");
        errorMessage.setBahasa("Berhasil Masuk");
        errorSchema.setErrorCode("000000");
        errorSchema.setErrorMessage(errorMessage);

        CommonResponse response = new CommonResponse();
        response.setOutputSchema(user);
        response.setErrorSchema(errorSchema);
        return response;
    }
}
