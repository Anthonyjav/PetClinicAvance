package com.tecsup.petclinic.util;

import com.tecsup.petclinic.entities.Vet;

public class TObjectCreatorVet {

    public static Vet createVet(Integer id, String firstName, String lastName) {
        return new Vet(id, firstName, lastName);
    }

    public static Vet createVetWithoutId(String firstName, String lastName) {
        return new Vet(firstName, lastName);
    }
}
