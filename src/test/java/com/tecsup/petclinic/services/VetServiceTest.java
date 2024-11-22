package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.util.TObjectCreatorVet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    void testGetAllVets() {
        List<Vet> vets = vetService.getAllVets();
        assertNotNull(vets);
    }

    @Test
    void testAddVet() {
        Vet newVet = TObjectCreatorVet.createVetWithoutId("John", "Doe");
        Vet savedVet = vetService.addVet(newVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    void testGetVetById() throws VetNotFoundException {
        Vet vet = vetService.getVetById(1);
        assertNotNull(vet);
    }
}
