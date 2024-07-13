package com.library.app;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import com.library.app.Service.Rents.RentsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentsServiceTests {

    @Mock
    private RentsRepo rentsRepo;
    @InjectMocks
    private RentsService rentsService;

    @Test
    void addNewRentTest() {
        Rent r = new Rent(BigInteger.valueOf(1), "lib id", BigInteger.valueOf(2));
        NewRentDTO newRentDTO = new NewRentDTO(BigInteger.valueOf(1), "lib id", BigInteger.valueOf(2));

        when(rentsRepo.save(any(Rent.class))).thenReturn(r);

        assertDoesNotThrow(() -> {
            rentsService.addNewRent(newRentDTO);
        });
    }
}



