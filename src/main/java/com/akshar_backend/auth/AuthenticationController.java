package com.akshar_backend.auth;


import com.akshar_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        System.out.println("called ji ");
        return ResponseEntity.ok().body(authenticationService.registerRequest(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("called in controller");
        return ResponseEntity.ok().body(authenticationService.authenticate(request));
    }

    @PutMapping("/verify/{otp}")
    public ResponseEntity<AuthenticationResponse> verifyEmail(@PathVariable String otp){
        return ResponseEntity.ok().body(authenticationService.verify(otp));
    }
}
