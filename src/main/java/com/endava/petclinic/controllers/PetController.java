package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.endava.petclinic.models.Visits;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class PetController {

    public static Pet generateRandomPet(){
        Faker faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Pet pet = new Pet();
        pet.setBirthDate(sdf.format(faker.date().birthday()));
        pet.setName(faker.name().username());
        pet.setOwner(new Owner("1297 Jere Fords", "Brakusshire", "Shauna", 210, "Gulgowski", "2321290623"));
        pet.setType(new PetType(28, "deer"));
        return pet;
    }
}
