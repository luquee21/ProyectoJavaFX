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
public interface IPerson {

    String getName();

    void setName(String name);

    String getLastName();

    void setLastName(String lastName);

    int getAge();

    void setAge(int age);

    String getEmail();

    void setEmail(String email);
}
