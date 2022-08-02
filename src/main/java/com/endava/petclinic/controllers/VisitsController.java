package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.endava.petclinic.models.Visits;
import com.github.javafaker.Faker;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class VisitsController {
    public static Visits generateRandonVisit() {
        Faker faker = new Faker();
        Visits visits = new Visits();
        PetType petType = new PetType(28, "deer");
        Owner owner = new Owner("1297 Jere Fords", "Brakusshire", "Shauna", 210, "Gulgowski", "2321290623");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        visits.setDate(sdf.format(faker.date().birthday()));
        visits.setDescription(faker.letterify("da"));
        visits.setPet(new Pet("1979/10/02", 125, "kelesi.wisoky", owner, petType));
        return visits;
    }
}
