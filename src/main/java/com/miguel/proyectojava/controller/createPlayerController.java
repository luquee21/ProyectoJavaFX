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
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    public boolean signup(String name, String lastname, String email, String user, String pass) {
        boolean result = false;
        if (!PlayerDAO.isAvailablePlayer(user)) {
            if (PlayerDAO.signup(name, lastname, email, user, pass) && ScoreDAO.createScore(user)) {
                result = true;
            }

        }
        

        return result;

    }

    public void signupFX() {
        String name = Fname.getText();
        String lastname = Flastname.getText();
        String email = Femail.getText();
        String user = Fuser.getText();
        String pass = DigestUtils.sha512Hex(Fpass.getText());
        if (!name.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pass.isEmpty()) {
            if (Utils.validateEmail(email)) {
                if (signup(name, lastname, email, user, pass)) {
                    showInformation("Exito", "Usuario registrado", "El usuario ha sido registrado con exito, por favor inicie sesion");
                    this.myStage.close();
                } else {
                    showWarning("Error", "Usuario", "Error al crear el usuario");
                }
            } else {
               showWarning("Error", "Email", "Introduce un email válido");
            }
        } else {
            showWarning("Error", "Datos", "Introduce todos los datos para registrarte");
        }
    }
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    public boolean showInformation(String title, String header, String content) {
        boolean r = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            r = true;
        } else {
            r = false;
        }
        return r;

    }
    
    public void back() throws IOException {
        this.myStage.close();
    }
}
