package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

import java.util.List;

/**
 *
 * @author jgomezm
 *
 */
public interface VetService {

    List<Vet> getAllVets();

    Vet getVetById(Integer id) throws VetNotFoundException;

    List<Vet> getVetsByFirstName(String firstName);

    List<Vet> getVetsByLastName(String lastName);

    Vet addVet(Vet vet);

    Vet updateVet(Integer id, Vet vet) throws VetNotFoundException;

    void deleteVet(Integer id) throws VetNotFoundException;
}
