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
import java.util.Optional;

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

    @Test
    void updateRentTest() {
        Rent r = new Rent(BigInteger.valueOf(1), "lib id", BigInteger.valueOf(2));
        NewRentDTO newRentDTO = new NewRentDTO(BigInteger.valueOf(1), "lib id", BigInteger.valueOf(2));
        when(rentsRepo.findById(BigInteger.valueOf(2500))).thenReturn(Optional.of(r));
        when(rentsRepo.findById(BigInteger.valueOf(2501))).thenReturn(Optional.empty());
        when(rentsRepo.save(any(Rent.class))).thenReturn(r);
        assertDoesNotThrow(() -> {
            rentsService.updateRent(BigInteger.valueOf(2500), newRentDTO);
            rentsService.updateRent(BigInteger.valueOf(2501), newRentDTO);
        });
        assertNotNull(rentsService.updateRent(BigInteger.valueOf(2500), newRentDTO));
        assertNull(rentsService.updateRent(BigInteger.valueOf(2501), newRentDTO));
    }

    @Test
    void deleteRentTest() {
        Rent r = new Rent(BigInteger.valueOf(1), "lib id", BigInteger.valueOf(2));
        when(rentsRepo.findById(BigInteger.valueOf(2500))).thenReturn(Optional.of(r));
        when(rentsRepo.findById(BigInteger.valueOf(2501))).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> {
            rentsService.deleteRent(BigInteger.valueOf(2500));
            rentsService.deleteRent(BigInteger.valueOf(2501));
        });
        assertTrue(rentsService.deleteRent(BigInteger.valueOf(2500)));
        assertFalse(rentsService.deleteRent(BigInteger.valueOf(2501)));
        verify(rentsRepo, times(2)).deleteById(any(BigInteger.class));
    }
}

