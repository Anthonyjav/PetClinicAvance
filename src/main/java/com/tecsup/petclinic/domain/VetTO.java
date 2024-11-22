package com.tecsup.petclinic.domain;

import lombok.Data;

@Data
public class VetTO {

    private Integer id;
    private String firstName;
    private String lastName;

    public VetTO() {}

    public VetTO(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

