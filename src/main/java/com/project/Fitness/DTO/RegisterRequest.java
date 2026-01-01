package com.project.Fitness.DTO;

import com.project.Fitness.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message =  "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank (message = "Password Cant be Blank")
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
