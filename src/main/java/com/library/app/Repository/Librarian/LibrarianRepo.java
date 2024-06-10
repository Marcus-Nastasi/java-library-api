package com.library.app.Repository.Librarian;

import com.library.app.Models.Librarian.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepo extends JpaRepository<Librarian, String> {

    UserDetails findByCpf(String cpf);
}



