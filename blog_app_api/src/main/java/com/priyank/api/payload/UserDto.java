package com.priyank.api.payload;

import java.util.HashSet;
import java.util.Set;

import com.priyank.api.entites.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
        private int User_id;
        private Set<RoleDto> roles=new HashSet<>();
        
        @NotEmpty
        @Size(min=4,message = "Username must be min of 4 characters")
        private String User_name;
        @Email(message="Email adress is not valid!!")
        private String email;
        @NotEmpty
        @Size(min=3,max=10,message = "Password must be min of 3 chars and max of 10 chars!!")
        private String password;
        @NotEmpty
        private String about;
        
}
