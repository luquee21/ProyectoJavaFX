/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.Player;
import com.miguel.proyectojava.model.PlayerDAO;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author migue
 */
public class startController {

    @FXML
    private TextField Fuser;

    @FXML
    private PasswordField Fpass;

   

    public void login() throws IOException {
        String user = Fuser.getText();
        String pass = DigestUtils.sha512Hex(Fpass.getText());
        Player aux = PlayerDAO.selectAllFromPlayerLogin(user);

        if (user.isEmpty() || pass.isEmpty()) {
            showWarning("Sesion", "Inicio de sesion", "Inicio de sesion incorrecto, compruebe el usuario y/o contraseña");
        } else {
            if (aux != null && aux.getPassword().equals(pass)) {
                if (showConfirm()) {
                    PlayerDAO.setP(aux);
                    App.setRoot("menu");
                } 

            } else {
                showWarning("Sesion", "Inicio de sesion", "Inicio de sesion incorrecto, compruebe el usuario y/o contraseña");
            }

        }

    }

    public boolean showConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Iniciar sesion");
        alert.setHeaderText("Inicio de sesion");
        alert.setContentText("¿Desea iniciar sesion?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public void signup() throws IOException {
        App.setRoot("createPlayer");
    }

}
