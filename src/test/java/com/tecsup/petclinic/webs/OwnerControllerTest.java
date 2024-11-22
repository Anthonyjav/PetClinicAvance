package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateOwner() throws Exception {
        Owner newOwner = new Owner(0, "Jane","Doe", "123 Main St", "Lucia", "1234567890");

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwner))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(newOwner.getFirstName()));
    }


    @Test
    public void testCreateOwnerWithEmptyFields() throws Exception {
        Owner invalidOwner = new Owner(0, "", "", "", "", "");

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(invalidOwner))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOwnerWithInvalidData() throws Exception {
        String invalidJson = "{\"id\":0,\"firstName\":\"Jane\",\"invalidField\":\"Value\"}";

        mockMvc.perform(post("/owners")
                        .content(invalidJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateOwnerPartial() throws Exception {
        Owner updatedOwner = new Owner(10, "UpdatedName", null, null, null, null);

        mockMvc.perform(put("/owners/10")
                        .content(om.writeValueAsString(updatedOwner))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(updatedOwner.getFirstName()));
    }
}
