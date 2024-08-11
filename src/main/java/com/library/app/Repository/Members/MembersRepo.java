package com.library.app.Repository.Members;

import com.library.app.Models.Members.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MembersRepo extends JpaRepository<Member, BigInteger> {

    @Query(nativeQuery = true, value = "SELECT * FROM members WHERE(cpf=?1);")
    Member findByCpf(String cpf);

    @Query(nativeQuery = true, value =
            "SELECT * FROM members " +
            "WHERE LOWER(cpf) LIKE LOWER(CONCAT(CONCAT('%', ?1), '%')) " +
            "OR LOWER(name) LIKE LOWER(CONCAT(CONCAT('%', ?1), '%'));")
    List<Member> searchMember(String data);
}



