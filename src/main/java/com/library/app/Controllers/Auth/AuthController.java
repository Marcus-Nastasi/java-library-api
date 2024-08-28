package com.library.app.Controllers.Auth;

import com.google.gson.Gson;
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

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000, http://192.168.0.76:3000")
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
    @Autowired
    private Gson gson;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO data) {
        var usernamePass = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var auth = authenticationManager.authenticate(usernamePass);
        UserDetails u = librarianRepo.findByCpf(data.cpf());
        if (passwordEncoder.matches(data.password(), u.getPassword())) return ResponseEntity
            .accepted()
            .header("Content-Type", "application/json")
            .body(gson.toJson(
                Map.of("data", List.of(Map.of("token", tokenService.generate(u.getUsername()), "cpf", u.getUsername())))
            ));
        return ResponseEntity
            .badRequest()
            .body(gson.toJson(
                    Map.of("data", List.of(Map.of("error", "cpf or password wrong")))
            ));
    }
}

