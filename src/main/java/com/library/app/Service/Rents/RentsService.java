package com.library.app.Service.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RentsService {

    @Autowired
    private RentsRepo rentsRepo;

    public Rent addNewRent(NewRentDTO data) {
        Rent r = new Rent(data.book_id(), data.librarian_id(), data.member_id());
        rentsRepo.save(r);
        return r;
    }

    public Rent updateRent(BigInteger id, NewRentDTO data) {
        if (rentsRepo.findById(id).isEmpty()) return null;

        Rent r = rentsRepo.findById(id).get();

        r.setBook_id(data.book_id());
        r.setLibrarian_id(data.librarian_id());
        r.setMember_id(data.member_id());
        rentsRepo.save(r);

        return r;
    }

    public String deleteRent(BigInteger id) {
        if (rentsRepo.findById(id).isEmpty()) return null;
        rentsRepo.deleteById(id);
        return "ok";
    }
}



