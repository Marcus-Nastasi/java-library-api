package com.library.app;

import com.library.app.DTOs.Librarian.AddLibrarianDTO;
import com.library.app.DTOs.Librarian.UpdLibrarianDTO;
import com.library.app.Models.Librarian.Librarian;
import com.library.app.Repository.Librarian.LibrarianRepo;
import com.library.app.Service.Auth.TokenService;
import com.library.app.Service.Librarians.LibrarianService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LibrarianServiceTests {

    @Mock
    private LibrarianRepo librarianRepo;
    @Mock
    private TokenService tokenService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private LibrarianService librarianService;

    @Test
    void addNewTest() {
        Librarian librarian = new Librarian("name", "cpf", "password");
        AddLibrarianDTO addLibrarianDTO = new AddLibrarianDTO("name", "cpf", "password");

        Mockito.when(librarianRepo.save(Mockito.any(Librarian.class))).thenReturn(librarian);

        Assertions.assertNotNull(librarianService.addNewLibrarian(addLibrarianDTO));
    }

    @Test
    void updateLibrarian() {
        Librarian librarian = new Librarian("name", "cpf", "password");
        UpdLibrarianDTO updLibrarianDTO = new UpdLibrarianDTO("new name", "new cpf", "password", "new pass");
        String tkn = "token";

        Mockito.when(librarianRepo.save(Mockito.any(Librarian.class))).thenReturn(librarian);
        Mockito.when(passwordEncoder.matches(updLibrarianDTO.oldPassword(), librarian.getPassword())).thenReturn(true);
        Mockito.when(tokenService.validate(tkn)).thenReturn(librarian.getCpf());
        Mockito.when(librarianRepo.findById(Mockito.any(String.class))).thenReturn(Optional.of(librarian));

        Assertions.assertDoesNotThrow(() -> {
            librarianService.updateLibrarian(updLibrarianDTO, "id", tkn);
        });
    }

    @Test
    void deleteLibrarian() {
        Librarian librarian = new Librarian("name", "cpf", "password");
        String tkn = "token";

        Mockito.when(tokenService.validate(Mockito.any(String.class))).thenReturn(librarian.getCpf());
        Mockito.when(librarianRepo.findById("1")).thenReturn(Optional.of(librarian));

        String result = librarianService.deleteLibrarian("1", tkn);

        Assertions.assertEquals("ok", result);
        Mockito.verify(librarianRepo, Mockito.times(1)).deleteById("1");
    }
}





