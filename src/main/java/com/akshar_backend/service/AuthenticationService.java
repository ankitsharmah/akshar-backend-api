package com.akshar_backend.service;

import com.akshar_backend.auth.AuthenticationRequest;
import com.akshar_backend.auth.AuthenticationResponse;
import com.akshar_backend.auth.RegisterRequest;
import com.akshar_backend.entity.User;
import com.akshar_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String registerRequest(RegisterRequest request) {
        // Generate OTP
        String otp = otpService.generateOtp(6); // Change the length as needed

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            //Saving user and setting otp in it
            var newUser = User.builder()
                    .email(request.getEmail())
                    .OtpGeneratedTime(LocalDateTime.now())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .name(request.getName())
                    .active(false)
                    .otp(otp)
                    .build();
            userRepository.save(newUser);
            // Send OTP via email
            emailService.sendOtpEmail(request.getEmail(), otp);
            return "Otp has been sent";
        }

        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        //if otp is expired
        emailService.sendOtpEmail(request.getEmail(), otp);
        return "Otp has been sent";
    }

    public AuthenticationResponse verify(String otp){
        User user = userRepository.findByOtp(otp);

        if(user != null && !user.isActive() && otp.equals(user.getOtp()) &&
                Duration.between(user.getOtpGeneratedTime() , LocalDateTime.now()).getSeconds()<(2*60)){
            user.setActive(true);
            user.setOtp(null);
            user.setOtpGeneratedTime(null);
            userRepository.save(user);

            //generating jwtToken
            String jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        //if otp is not valid
        return AuthenticationResponse.builder()
                .token("Invalid Otp")
                .build();
    }
//    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        System.out.println("in service");
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()));
//        System.out.println("after ji");
//        User user = userRepository.findByEmail(request.getEmail());
//
//        if (user!= null && user.isEnabled()){
//
//            String jwtToken = jwtService.generateToken(user);
//
//            return AuthenticationResponse.builder()
//                    .token(jwtToken)
//                    .build();
//        }
//        return AuthenticationResponse.builder()
//                .token("please verify your email")
//                .build();
//    }
//


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user==null || !user.isActive()){
            return AuthenticationResponse.builder()
                    .token("verify your email first ")
                    .build();
        }
        System.out.println("before");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        System.out.println("after authenticcation");
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();

    }
}
