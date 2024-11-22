package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import com.tecsup.petclinic.util.TObjectCreatorOwner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OwnerServiceMockitoTest {

    private OwnerService ownerService;

    @Mock
    private OwnerRepository repository;

    @BeforeEach
    void setUp() {
        this.ownerService = new OwnerServiceImpl(this.repository);
    }

    @Test
    public void testFindOwnerById() {
        Owner ownerExpected = TObjectCreatorOwner.getOwner();

        Mockito.when(this.repository.findById(ownerExpected.getId()))
                .thenReturn(Optional.of(ownerExpected));
        try {
            Owner owner = this.ownerService.findById(ownerExpected.getId());
            assertEquals(ownerExpected.getFirstName(), owner.getFirstName());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateOwner() {
        Owner newOwner = TObjectCreatorOwner.newOwner();
        Owner newOwnerCreated = TObjectCreatorOwner.newOwnerCreated();

        Mockito.when(this.repository.save(newOwner))
                .thenReturn(newOwnerCreated);

        Owner ownerCreated = this.ownerService.create(newOwner);

        assertNotNull(ownerCreated.getId());
        assertEquals(newOwnerCreated.getFirstName(), ownerCreated.getFirstName());
    }

    @Test
    public void testUpdateOwner() {
        Owner updatedOwner = TObjectCreatorOwner.newOwnerForUpdate();

        Mockito.when(this.repository.save(updatedOwner))
                .thenReturn(updatedOwner);

        Owner ownerUpdated = this.ownerService.update(updatedOwner);

        assertEquals(updatedOwner.getFirstName(), ownerUpdated.getFirstName());
    }

    @Test
    public void testDeleteOwner() {
        Owner ownerToDelete = TObjectCreatorOwner.newOwnerForDelete();

        Mockito.when(this.repository.findById(ownerToDelete.getId()))
                .thenReturn(Optional.of(ownerToDelete));
        Mockito.doNothing().when(this.repository).delete(ownerToDelete);

        try {
            this.ownerService.delete(ownerToDelete.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateOwnerWithEmptyFields() throws Exception {
        Owner invalidOwner = new Owner(0, "", "", "", "", "");

        Mockito.when(this.repository.save(invalidOwner))
                .thenThrow(new IllegalArgumentException("Invalid owner data"));

        try {
            this.ownerService.create(invalidOwner);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid owner data", e.getMessage());
        }
    }

    @Test
    public void testUpdateOwnerPartial() throws Exception {
        Owner updatedOwner = new Owner(1000, "UpdatedName", null, null, null, null);

        Mockito.when(this.repository.save(updatedOwner))
                .thenReturn(updatedOwner);

        Owner ownerUpdated = this.ownerService.update(updatedOwner);

        assertEquals(updatedOwner.getFirstName(), ownerUpdated.getFirstName());
    }

    @Test
    public void testFindOwnerByIdNotFound() throws Exception {
        Mockito.when(this.repository.findById(999)).thenReturn(Optional.empty());

        try {
            this.ownerService.findById(999);
            fail("Expected OwnerNotFoundException");
        } catch (OwnerNotFoundException e) {
            assertEquals("Owner not found", e.getMessage());
        }
    }

    @Test
    public void testDeleteOwnerNotFound() throws Exception {
        Mockito.doThrow(new OwnerNotFoundException("Owner not found"))
                .when(this.repository).delete(Mockito.any());

        try {
            this.ownerService.delete(999);
            fail("Expected OwnerNotFoundException");
        } catch (OwnerNotFoundException e) {
            assertEquals("Owner not found", e.getMessage());
        }
    }

    @Test
    public void testCreateOwnerWithInvalidData() throws Exception {
        String invalidJson = "{\"id\":0,\"firstName\":\"Jane\",\"invalidField\":\"Value\"}";
        assertThrows(IllegalArgumentException.class, () -> {
        });
    }
}
