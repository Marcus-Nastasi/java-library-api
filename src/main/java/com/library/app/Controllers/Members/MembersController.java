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
        if (membersRepo.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(membersRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Member> getSingle(@PathVariable BigInteger id) {
        if (membersRepo.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(membersRepo.findById(id).get());
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Member> addNewMember(@RequestBody @Valid NewMemberDTO data) {
        Member m = membersService.addNewMember(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Member> update(@PathVariable BigInteger id , @RequestBody @Valid NewMemberDTO data) {
        Member m = membersService.updateMember(id, data);
        if (m == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> del(@PathVariable BigInteger id) {
        String deletion = membersService.deleteMember(id);
        if (deletion == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("member empty on this id");
        return ResponseEntity.accepted().build();
    }
}






