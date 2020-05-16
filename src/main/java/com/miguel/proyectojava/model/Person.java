/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;



/**
 *
 * @author migue
 */
public abstract class Person implements IPerson {
    protected String name;
    protected String lastname;
    protected String email;

    public Person(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public Person() {
    }
    
    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    @Override
    public String getEmail() {
       return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
   
    

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", lastname=" + lastname + ", email=" + email + '}';
    }
    
    

   
}
