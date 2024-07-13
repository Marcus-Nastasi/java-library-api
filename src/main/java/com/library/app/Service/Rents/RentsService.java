package com.library.app.Service.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentsService {

    @Autowired
    private RentsRepo rentsRepo;

    public Rent addNewRent(NewRentDTO data) {
        Rent r = new Rent(data.book_id(), data.librarian_id(), data.member_id());
        rentsRepo.save(r);
        return r;
    }
}



