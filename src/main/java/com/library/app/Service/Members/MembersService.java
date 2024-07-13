package com.library.app.Service.Members;

import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.Models.Members.Member;
import com.library.app.Repository.Members.MembersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MembersService {

    @Autowired
    private MembersRepo membersRepo;

    public Member addNewMember(NewMemberDTO data) {
        if (membersRepo.findByCpf(data.cpf()) != null) return null;

        Member m = new Member(data.name(), data.cpf(), data.type(), data.booksIssued(), data.booksLimit(), data.phone());

        membersRepo.save(m);

        return m;
    }

    public Member updateMember(BigInteger id, NewMemberDTO data) {
        if (membersRepo.findById(id).isEmpty()) return null;

        Member m = membersRepo.findById(id).orElseThrow();

        m.setName(data.name());
        m.setCpf(data.cpf());
        m.setBooksIssued(data.booksIssued());
        m.setType(data.type());
        m.setBooksLimit(data.booksLimit());
        m.setPhone(data.phone());

        membersRepo.save(m);

        return m;
    }

    public String deleteMember(BigInteger id) {
        if (membersRepo.findById(id).isEmpty()) return null;

        membersRepo.deleteById(id);

        return "ok";
    }
}



