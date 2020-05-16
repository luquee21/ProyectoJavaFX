/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author migue
 */
public class Utils {

    public static boolean validateEmail(String email) {
        boolean result = false;
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                result = true;
            }
            
        }
        return result;
    }
}
