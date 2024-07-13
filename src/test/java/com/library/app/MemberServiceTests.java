package com.library.app;

import com.library.app.DTOs.Members.NewMemberDTO;
import com.library.app.Enums.Members.MemberType;
import com.library.app.Models.Members.Member;
import com.library.app.Repository.Members.MembersRepo;
import com.library.app.Service.Members.MembersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTests {

    @Mock
    private MembersRepo membersRepo;
    @InjectMocks
    private MembersService membersService;

    @Test
    void addNewMemberTest() {
        Member m = new Member("Name", "CPF", MemberType.REGULAR, 2, 2, "123213231");
        NewMemberDTO newMemberDTO = new NewMemberDTO("Name", "CPF", MemberType.REGULAR, "123213231", 2, 2);
        NewMemberDTO newMemberDTONull = new NewMemberDTO("Name", "CPFNull", MemberType.REGULAR, "21323131321", 2, 2);

        when(membersRepo.findByCpf(newMemberDTO.cpf())).thenReturn(null);
        when(membersRepo.findByCpf(newMemberDTONull.cpf())).thenReturn(m);
        when(membersRepo.save(any(Member.class))).thenReturn(m);

        assertDoesNotThrow(() -> {
            membersService.addNewMember(newMemberDTO);
        });

        assertEquals(m.getCpf(), membersService.addNewMember(newMemberDTO).getCpf());
        assertNotNull(membersService.addNewMember(newMemberDTO));
        assertNull(membersService.addNewMember(newMemberDTONull));

        verify(membersRepo, times(3)).save(any(Member.class));
    }

    @Test
    void updateMemberTest() {
        Member m = new Member("Name", "CPF", MemberType.REGULAR, 2, 2, "123213231");
        NewMemberDTO newMemberDTO = new NewMemberDTO("Name", "CPF", MemberType.REGULAR, "21323131321", 2, 2);

        when(membersRepo.findById(BigInteger.valueOf(2500))).thenReturn(Optional.of(m));
        when(membersRepo.findById(BigInteger.valueOf(2501))).thenReturn(Optional.empty());
        when(membersRepo.save(any(Member.class))).thenReturn(m);

        assertDoesNotThrow(() -> {
            membersService.updateMember(BigInteger.valueOf(2500), newMemberDTO);
        });

        assertNotNull(membersService.updateMember(BigInteger.valueOf(2500), newMemberDTO));
        assertNull(membersService.updateMember(BigInteger.valueOf(2501), newMemberDTO));
        assertEquals(m, membersService.updateMember(BigInteger.valueOf(2500), newMemberDTO));

        verify(membersRepo, times(3)).save(m);
    }

    @Test
    void deleteMemberTest() {
        Member m = new Member("Name", "CPF", MemberType.REGULAR, 2, 2, "123213231");

        when(membersRepo.findById(BigInteger.valueOf(2500))).thenReturn(Optional.of(m));
        when(membersRepo.findById(BigInteger.valueOf(2501))).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            membersService.deleteMember(BigInteger.valueOf(2500));
        });

        assertEquals("ok", membersService.deleteMember(BigInteger.valueOf(2500)));
        assertNull(membersService.deleteMember(BigInteger.valueOf(2501)));

        verify(membersRepo, times(2)).deleteById(any(BigInteger.class));
    }
}



