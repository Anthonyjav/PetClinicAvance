package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testFindOwnerById() throws OwnerNotFoundException {
        Owner owner = ownerService.findById(1);
        assertNotNull(owner);
    }

    @Test
    public void testFindAllOwners() {
        List<Owner> owners = ownerService.findAll();
        assertFalse(owners.isEmpty());
    }
}
