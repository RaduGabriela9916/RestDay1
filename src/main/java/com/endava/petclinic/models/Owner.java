package com.endava.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    private String address;
    private String city;
    private String firstName;
    private Integer id;
    private String lastName;
    private String telephone;

    public Owner(){
    }

    public Owner(String address, String city, String firstName, Integer id, String lastName, String telephone) {
        this.address = address;
        this.city = city;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public Owner(String address, String city, String firstName, String lastName, String telephone) {
        this.address = address;
        this.city = city;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public Integer getId(){
        return id;
    }

    public void setId( Integer id ){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName( String firstName ){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName( String lastName ){
        this.lastName = lastName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress( String address ){
        this.address = address;
    }

    public String getCity(){
        return city;
    }

    public void setCity( String city ){
        this.city = city;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone( String telephone ){
        this.telephone = telephone;
    }

    @Override
    public boolean equals( Object o ){
        if ( this == o ) return true;
        if ( !( o instanceof Owner ) ) return false;
        Owner owner = ( Owner ) o;
        return Objects.equals( firstName, owner.firstName ) && Objects.equals( lastName, owner.lastName ) && Objects.equals( address, owner.address ) && Objects.equals( city, owner.city ) && Objects.equals( telephone, owner.telephone );
    }

    @Override
    public int hashCode(){
        return Objects.hash( firstName, lastName, address, city, telephone );
    }

    @Override
    public String toString(){
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
