package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import com.tecsup.petclinic.util.TObjectCreatorOwner;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OwnerControllerMockitoTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OwnerService ownerService;

    @Test
    public void testFindOwnerById() throws Exception {
        Owner owner = TObjectCreatorOwner.getOwner();

        Mockito.when(ownerService.findById(1)).thenReturn(owner);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(owner.getFirstName()));
    }

    @Test
    public void testFindOwnerByIdNotFound() throws Exception {
        Mockito.when(ownerService.findById(999)).thenThrow(new OwnerNotFoundException("Owner not found"));

        mockMvc.perform(get("/owners/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Owner not found"));
    }
}
