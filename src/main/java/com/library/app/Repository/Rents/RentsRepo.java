package com.library.app.Repository.Rents;

import com.library.app.Models.Rents.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RentsRepo extends JpaRepository<Rent, BigInteger> {
}




