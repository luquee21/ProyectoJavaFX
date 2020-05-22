/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.model.PlayerDAO;
import com.miguel.proyectojava.model.ScoreDAO;
import com.miguel.proyectojava.utils.Utils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author migue
 */
public class createPlayerController {

    @FXML
    private TextField Fname;

    @FXML
    private TextField Flastname;

    @FXML
    private TextField Femail;

    @FXML
    private TextField Fuser;

    @FXML
    private PasswordField Fpass;
    
     private startController parent;

    private Object params;
    private Stage myStage;
    
    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(startController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }

    
    /**
     * Recibe los datos necesarios para registar al usuario
     * @param name nombre del jugador
     * @param lastname apellidos del jugador
     * @param email email del jugador
     * @param user usuario del jugador
     * @param pass contraseña del jugador
     * @return devuelve cierto si ha conseguido registrar al usuario en la base de datos
     */
    public boolean signup(String name, String lastname, String email, String user, String pass) {
        boolean result = false;
        if (!PlayerDAO.isAvailablePlayer(user)) {
            if (PlayerDAO.signup(name, lastname, email, user, pass) && ScoreDAO.createScore(user)) {
                result = true;
            }

        }
        

        return result;

    }
    
    /**
     * Funcion asociada a javafx para recoger los datos del usuario para su posterior registro 
     */

    public void signupFX() {
        String name = Fname.getText();
        String lastname = Flastname.getText();
        String email = Femail.getText();
        String user = Fuser.getText();
        String pass = DigestUtils.sha512Hex(Fpass.getText());
        if (!name.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pass.isEmpty()) {
            if (Utils.validateEmail(email)) {
                if (signup(name, lastname, email, user, pass)) {
                    parent.showInformation("Exito", "Usuario registrado", "El usuario ha sido registrado con exito, por favor inicie sesion");
                    this.myStage.close();
                } else {
                    parent.showWarning("Error", "Usuario", "Error al crear el usuario");
                }
            } else {
               parent.showWarning("Error", "Email", "Introduce un email válido");
            }
        } else {
            parent.showWarning("Error", "Datos", "Introduce todos los datos para registrarte");
        }
    }
    
    /**
     * Funcion para cerrar la ventana
     * @throws IOException 
     */
    public void back() throws IOException {
        this.myStage.close();
    }
}
