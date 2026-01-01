package com.project.Fitness.Service;

import com.project.Fitness.DTO.LoginRequest;
import com.project.Fitness.DTO.RegisterRequest;
import com.project.Fitness.DTO.UserResponse;
import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.model.User;
import com.project.Fitness.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request)
    {
        //BuilderPattern
        UserRole role = request.getRole() != null ? request.getRole() : UserRole.USER;
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastName())
                .role(role)
                .build();
        //constructor method
//        User user = new User(
//                null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName(),
//                null,
//                null,
//                List.of(),
//                List.of()
//        );
        User savedUser = userRepository.save(user);
    return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;

    }

    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null)
            throw new RuntimeException("Invalid Credentials") ;
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("Invalid Credentials") ;        }
        return user;
    }
}
