package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jgomezm
 *
 */
@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    @Override
    public Vet getVetById(Integer id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);
        if (vet.isPresent()) {
            return vet.get();
        } else {
            throw new VetNotFoundException("Vet with ID " + id + " not found.");
        }
    }

    @Override
    public List<Vet> getVetsByFirstName(String firstName) {
        return vetRepository.findByFirstName(firstName);
    }

    @Override
    public List<Vet> getVetsByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }

    @Override
    public Vet addVet(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet updateVet(Integer id, Vet vet) throws VetNotFoundException {
        if (!vetRepository.existsById(id)) {
            throw new VetNotFoundException("Vet with ID " + id + " not found.");
        }
        vet.setId(id);
        return vetRepository.save(vet);
    }

    @Override
    public void deleteVet(Integer id) throws VetNotFoundException {
        if (!vetRepository.existsById(id)) {
            throw new VetNotFoundException("Vet with ID " + id + " not found.");
        }
        vetRepository.deleteById(id);
    }
}
