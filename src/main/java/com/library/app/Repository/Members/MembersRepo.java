package com.library.app.Repository.Members;

import com.library.app.Models.Members.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface MembersRepo extends JpaRepository<Member, BigInteger> {
}




