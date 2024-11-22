package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author jgomezm
 *
 */
@RestController
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        List<OwnerTO> ownerTOList = mapper.toOwnerTOList(owners);
        return ResponseEntity.ok(ownerTOList);
    }

    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {
        if (ownerTO.getFirstName() == null || ownerTO.getFirstName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (ownerTO.getLastName() == null || ownerTO.getLastName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        Owner newOwner = mapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = mapper.toOwnerTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            OwnerTO ownerTO = mapper.toOwnerTO(owner);
            return ResponseEntity.ok(ownerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            owner.setFirstName(ownerTO.getFirstName());
            owner.setLastName(ownerTO.getLastName());
            owner.setAddress(ownerTO.getAddress());
            owner.setCity(ownerTO.getCity());
            owner.setTelephone(ownerTO.getTelephone());
            Owner updatedOwner = ownerService.update(owner);
            OwnerTO updatedOwnerTO = mapper.toOwnerTO(updatedOwner);
            return ResponseEntity.ok(updatedOwnerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted Owner with ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
