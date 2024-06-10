package com.library.app.Service.Auth;

import com.library.app.Repository.Librarian.LibrarianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private LibrarianRepo librarianRepo;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return librarianRepo.findByCpf(cpf);
    }
}



