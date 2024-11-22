package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.services.VetService;
import com.tecsup.petclinic.util.TObjectCreatorVet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VetControllerTest {

    @Autowired
    private VetController vetController;

    @Test
    void testFindAllVets() {
        ResponseEntity<?> response = vetController.findAllVets();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testCreateVet() {
        Vet vet = TObjectCreatorVet.createVetWithoutId("Alice", "Smith");
        ResponseEntity<?> response = vetController.create(vet);
        assertEquals(201, response.getStatusCodeValue());
    }
}
