package com.library.app.Controllers.Members;

import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.Models.Members.Member;
import com.library.app.Repository.Members.MembersRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/members")
public class MembersController {

    @Autowired
    private MembersRepo membersRepo;

    @PostMapping(value = "/new")
    public ResponseEntity<String> addNewMember(@RequestBody @Valid NewMemberDTO data) {
        Member m = new Member(data.name(), data.cpf(), data.type(), data.booksIssued(), data.booksLimit(), data.phone());
        membersRepo.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}






