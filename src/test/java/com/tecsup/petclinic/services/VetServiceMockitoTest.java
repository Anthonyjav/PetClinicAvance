package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.util.TObjectCreatorVet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VetServiceMockitoTest {

    private final VetRepository vetRepository = mock(VetRepository.class);
    private final VetService vetService = new VetServiceImpl(vetRepository);

    @Test
    void testGetVetById() throws VetNotFoundException {
        Vet vet = TObjectCreatorVet.createVet(1, "John", "Doe");
        when(vetRepository.findById(1)).thenReturn(Optional.of(vet));

        Vet result = vetService.getVetById(1);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testVetNotFoundException() {
        when(vetRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(VetNotFoundException.class, () -> vetService.getVetById(99));
    }
}
