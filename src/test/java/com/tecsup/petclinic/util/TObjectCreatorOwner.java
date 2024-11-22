package com.tecsup.petclinic.util;

import com.tecsup.petclinic.entities.Owner;

public class TObjectCreatorOwner {

    public static Owner getOwner() {
        return new Owner(1, "John", "Doe", "123 Main St", "Springfield", "1234567890");
    }

    public static Owner newOwner() {
        return new Owner(0, "Jane", "Smith", "456 Oak St", "Shelbyville", "0987654321");
    }

    public static Owner newOwnerCreated() {
        Owner owner = newOwner();
        owner.setId(1000);
        return owner;
    }

    public static Owner newOwnerForUpdate() {
        return new Owner(1000, "Jane", "Doe", "789 Pine St", "Shelbyville", "1122334455");
    }

    public static Owner newOwnerForDelete() {
        return new Owner(1001, "Tom", "Brown", "321 Maple St", "Springfield", "5566778899");
    }
}
