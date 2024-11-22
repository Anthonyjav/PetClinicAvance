package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.services.VetService;
import com.tecsup.petclinic.domain.VetTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class VetController {

    private final VetService vetService;
    private final VetMapper mapper;

    public VetController(VetService vetService, VetMapper mapper) {
        this.vetService = vetService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/vets")
    public ResponseEntity<List<VetTO>> findAllVets() {
        List<Vet> vets = vetService.getAllVets();
        log.info("vets: " + vets);
        vets.forEach(item -> log.info("Vet >>  {} ", item));

        List<VetTO> vetTOList = this.mapper.toVetTOList(vets);
        log.info("vetTOList: " + vetTOList);
        vetTOList.forEach(item -> log.info("VetTO >>  {} ", item));

        return ResponseEntity.ok(vetTOList);
    }


    @PostMapping(value = "/vets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VetTO> create(@RequestBody Vet vetTO) {
        Vet newVet = this.mapper.toVet(vetTO);
        VetTO newVetTO = this.mapper.toVetTO(vetService.addVet(newVet));

        return ResponseEntity.status(HttpStatus.CREATED).body(newVetTO);
    }


    @GetMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> findById(@PathVariable Integer id) {
        VetTO vetTO = null;

        try {
            Vet vet = vetService.getVetById(id);
            vetTO = this.mapper.toVetTO(vet);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vetTO);
    }


    @PutMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> update(@RequestBody VetTO vetTO, @PathVariable Integer id) {
        VetTO updatedVetTO = null;

        try {
            Vet updateVet = vetService.getVetById(id);
            updateVet.setFirstName(vetTO.getFirstName());
            updateVet.setLastName(vetTO.getLastName());

            vetService.updateVet(id, updateVet);
            updatedVetTO = this.mapper.toVetTO(updateVet);

        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedVetTO);
    }

    @DeleteMapping(value = "/vets/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            vetService.deleteVet(id);
            return ResponseEntity.ok("Deleted vet with ID: " + id);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
