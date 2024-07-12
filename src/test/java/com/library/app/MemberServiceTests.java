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
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MemberServiceTests {

    @Mock
    private MembersRepo membersRepo;
    @InjectMocks
    private MembersService membersService;

    @Test
    void addNewMemberTest() {
        Member m = new Member("Name", "CPF", MemberType.REGULAR, 2, 2, "123213231");
        NewMemberDTO newMemberDTO = new NewMemberDTO("Name", "CPF", MemberType.REGULAR, "21323131321", 2, 2);

        when(membersRepo.save(any(Member.class))).thenReturn(m);

        assertDoesNotThrow(() -> {
            membersService.addNewMember(newMemberDTO);
        });
    }

    @Test
    void updateMemberTest() {
        Member m = new Member("Name", "CPF", MemberType.REGULAR, 2, 2, "123213231");
        NewMemberDTO newMemberDTO = new NewMemberDTO("Name", "CPF", MemberType.REGULAR, "21323131321", 2, 2);

        when(membersRepo.findById(any(BigInteger.class))).thenReturn(Optional.of(m));
        when(membersRepo.save(any(Member.class))).thenReturn(m);

        assertDoesNotThrow(() -> {
            membersService.updateMember(BigInteger.valueOf(2), newMemberDTO);
        });

        assertEquals(m, membersService.updateMember(BigInteger.valueOf(2), newMemberDTO));

        verify(membersRepo, times(2)).save(m);
    }
}



