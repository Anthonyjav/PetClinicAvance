package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.services.VetService;
import com.tecsup.petclinic.util.TObjectCreatorVet;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VetControllerMockitoTest {

    @Mock
    private VetMapper mapper;

    private final VetService vetService = mock(VetService.class);
    private final VetController vetController = new VetController(vetService, null);

    @Test
    void testFindAllVets() {
        when(vetService.getAllVets()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = vetController.findAllVets();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteVet() throws VetNotFoundException {
        doNothing().when(vetService).deleteVet(1);
        ResponseEntity<?> response = vetController.delete(1);
        assertEquals(200, response.getStatusCodeValue());
    }
}
