package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<MyUser> getAllUser(MyUser myUser){
        MyUser adminUser = authRepository.findMyUserById(myUser.getId());
        if(adminUser == null){
            throw new ApiException("admin not found");
        }
        if (adminUser.getRole().equals("ADMIN")){
            return authRepository.findAll();
        }
        throw new ApiException("not admin");
    }

    public void register(MyUser myUser){
        MyUser checkUser = authRepository.findMyUserByUsername(myUser.getUsername());
        if(checkUser != null){
            throw new ApiException("username exist");
        }
        myUser.setRole("USER");
        String hashpassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashpassword);
        authRepository.save(myUser);
    }
}
