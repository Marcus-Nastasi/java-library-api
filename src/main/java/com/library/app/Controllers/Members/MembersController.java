package com.library.app.Controllers.Members;

import com.google.gson.Gson;
import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.DTOs.Members.SearchMemberDTO;
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
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000, http://192.168.0.76:3000")
@RequestMapping(value = "/api/members")
public class MembersController {

    @Autowired
    private MembersRepo membersRepo;
    @Autowired
    private MembersService membersService;
    @Autowired
    private Gson gson;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Member>> getAll() {
        return (membersRepo.findAll().isEmpty())
            ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            : ResponseEntity.ok(membersRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Member> getSingle(@PathVariable BigInteger id) {
        return (membersRepo.findById(id).isEmpty())
            ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            : ResponseEntity.ok(membersRepo.findById(id).get());
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Member>> search(@RequestBody @Valid SearchMemberDTO data) {
        List<Member> memberList = membersService.searchMember(data.data());
        if (memberList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(memberList);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> addNewMember(@RequestBody @Valid NewMemberDTO data) {
        Member m = membersService.addNewMember(data);
        if (m == null) return ResponseEntity.status(HttpStatus.CONFLICT)
            .header("Content-Type", "application/json")
            .body(gson.toJson(Map.of("data", List.of(Map.of("error", "member already registered")))));
        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(Map.of("data", List.of("member", m))));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Member> update(@PathVariable BigInteger id , @RequestBody @Valid NewMemberDTO data) {
        Member m = membersService.updateMember(id, data);
        if (m == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> del(@PathVariable BigInteger id) {
        boolean deletion = membersService.deleteMember(id);
        if (!deletion) return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .header("Content-Type", "application/json")
            .body(gson.toJson(Map.of("error", "member not found")));
        return ResponseEntity.accepted().build();
    }
}


