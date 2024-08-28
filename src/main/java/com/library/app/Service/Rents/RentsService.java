package com.library.app.Service.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Members.Member;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Members.MembersRepo;
import com.library.app.Repository.Rents.RentsRepo;
import com.library.app.Service.Members.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RentsService {

    @Autowired
    private RentsRepo rentsRepo;
    @Autowired
    private MembersRepo membersRepo;

    public Rent addNewRent(NewRentDTO data) {
        Member m = membersRepo.findById(data.member_id()).orElseThrow();
        Rent r = new Rent(data.book_id(), data.librarian_id(), data.member_id(), m);
        rentsRepo.save(r);
        return r;
    }

    public Rent updateRent(BigInteger id, NewRentDTO data) {
        if (rentsRepo.findById(id).isEmpty()) return null;
        Member m = membersRepo.findById(data.member_id()).orElseThrow();
        Rent r = rentsRepo.findById(id).get();
        r.setBook_id(data.book_id());
        r.setLibrarian_id(data.librarian_id());
        r.setMember_id(data.member_id());
        rentsRepo.save(r);
        return r;
    }

    public boolean deleteRent(BigInteger id) {
        if (rentsRepo.findById(id).isEmpty()) return false;
        rentsRepo.deleteById(id);
        return true;
    }
}

