package com.project.Fitness.Controller;

import com.project.Fitness.DTO.LoginRequest;
import com.project.Fitness.DTO.LoginResponse;
import com.project.Fitness.DTO.RegisterRequest;
import com.project.Fitness.DTO.UserResponse;
import com.project.Fitness.Security.JwtUtils;
import com.project.Fitness.Service.UserService;
import com.project.Fitness.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        //Validation code (No need to write complex validation code instead mak use of spring @ Annotations)//Add Validations Dependencies
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {
        try
        {
            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(token,userService.mapToResponse(user)));
        } catch (AuthenticationException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
