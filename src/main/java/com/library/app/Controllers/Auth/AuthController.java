package com.library.app.Controllers.Auth;

import com.library.app.DTOs.Auth.LoginDTO;
import com.library.app.Repository.Librarian.LibrarianRepo;
import com.library.app.Service.Auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login/")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO data) {
        var usernamePass = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var auth = authenticationManager.authenticate(usernamePass);
        UserDetails u = librarianRepo.findByCpf(data.cpf());

        return( passwordEncoder.matches(data.password(), u.getPassword()) ?
            ResponseEntity.accepted().body(tokenService.generate(u.getUsername())) :
            ResponseEntity.badRequest().build()
        );
    }
}






