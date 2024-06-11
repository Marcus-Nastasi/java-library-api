package com.library.app.Controllers.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rents")
public class RentController {

    @Autowired
    private RentsRepo rentsRepo;

    @PostMapping(value = "/new/")
    public ResponseEntity<String> newRent(@RequestBody @Valid NewRentDTO data) {
        Rent r = new Rent(data.book_id(), data.librarian_id(), data.member_id());
        rentsRepo.save(r);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}




