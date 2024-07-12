package com.library.app.Service.Members;

import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.Models.Members.Member;
import com.library.app.Repository.Members.MembersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersService {

    @Autowired
    private MembersRepo membersRepo;

    public Member addNewMember(NewMemberDTO data) {
        Member m = new Member(data.name(), data.cpf(), data.type(), data.booksIssued(), data.booksLimit(), data.phone());
        membersRepo.save(m);
        return m;
    }

    
}



