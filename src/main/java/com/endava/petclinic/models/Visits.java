package com.endava.petclinic.models;

import java.util.Objects;

public class Visits {

    private String date;
    private String description;
    private Integer id;
    private Pet pet;

    public Visits() {
    }

    public Visits(String date, String description, Integer id, Pet pet) {
        this.date = date;
        this.description = description;
        this.id = id;
        this.pet = pet;
    }

    public Visits(String date, String description, Pet pet) {
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visits visits = (Visits) o;
        return Objects.equals(date, visits.date) && Objects.equals(description, visits.description) && Objects.equals(id, visits.id) && Objects.equals(pet, visits.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, id, pet);
    }

    @Override
    public String toString() {
        return "Visits{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", pet=" + pet +
                '}';
    }
}
