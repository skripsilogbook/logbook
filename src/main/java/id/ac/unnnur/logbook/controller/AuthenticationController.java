package id.ac.unnnur.logbook.controller;

import id.ac.unnnur.logbook.payload.CommonResponse;
import id.ac.unnnur.logbook.payload.request.LoginRequest;
import id.ac.unnnur.logbook.payload.request.RegisterRequest;
import id.ac.unnnur.logbook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final UserService service;

    public AuthenticationController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequest request) {
        CommonResponse response = service.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody RegisterRequest request) {
        CommonResponse response = service.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
