package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity userRegistered(@RequestBody @Valid MyUser user) throws ApiException{
        authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered successfully"));
    }

    @GetMapping("/get-users")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser myUser){
        List<MyUser> users = authService.getAllUser(myUser);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
