package com.library.app.Controllers.Members;

import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.Models.Members.Member;
import com.library.app.Repository.Members.MembersRepo;
import com.library.app.Service.Members.MembersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/members")
public class MembersController {

    @Autowired
    private MembersRepo membersRepo;
    @Autowired
    private MembersService membersService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(membersRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Member> getSingle(@PathVariable BigInteger id) {
        return ResponseEntity.ok(membersRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Member> addNewMember(@RequestBody @Valid NewMemberDTO data) {
        Member m = membersService.addNewMember(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable BigInteger id , @RequestBody @Valid NewMemberDTO data) {
        Member m = membersRepo.findById(id).orElseThrow();
        m.setName(data.name());
        m.setCpf(data.cpf());
        m.setBooksIssued(data.booksIssued());
        m.setType(data.type());
        m.setBooksLimit(data.booksLimit());
        m.setPhone(data.phone());
        membersRepo.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> del(@PathVariable BigInteger id) {
        membersRepo.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}






