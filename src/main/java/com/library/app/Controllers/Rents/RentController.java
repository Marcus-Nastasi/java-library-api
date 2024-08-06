package com.library.app.Controllers.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import com.library.app.Service.Rents.RentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000, http://192.168.0.76:3000")
@RequestMapping(value = "/api/rents")
public class RentController {

    @Autowired
    private RentsRepo rentsRepo;
    @Autowired
    private RentsService rentsService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Rent>> getAll() {
        if (rentsRepo.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(rentsRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Rent> getSingle(@PathVariable BigInteger id) {
        if (rentsRepo.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(rentsRepo.findById(id).get());
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Rent> newRent(@RequestBody @Valid NewRentDTO data) {
        Rent r = rentsService.addNewRent(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Rent> update(@PathVariable BigInteger id, @RequestBody @Valid NewRentDTO data) {
        Rent r = rentsService.updateRent(id, data);
        if (r == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        String deletion = rentsService.deleteRent(id);
        if (deletion == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}


