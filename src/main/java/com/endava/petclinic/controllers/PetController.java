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

    public static Pet generateNewRandomPet(){
        Faker faker = new Faker();
        Pet pet = new Pet();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        pet.setName(faker.dog().name());
        pet.setOwner(OwnerController.genarateNewRandomOwner());
        pet.setType(new PetType(faker.animal().name()));
        pet.setBirthDate(formatter.format(faker.date().birthday(1,10)));
        return pet;
    }
}
