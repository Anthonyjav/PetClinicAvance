package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;

/**
 *
 * @author jgomezm
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    List<Owner> findByLastName(String lastName);

    @Override
    List<Owner> findAll();
}
