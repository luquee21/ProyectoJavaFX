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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private boolean login(String user, String pass) {
        boolean result = false;
        Player aux = PlayerDAO.selectAllFromPlayerLogin(user);
        if (aux != null && aux.getPassword().equals(pass)) {
            result = true;
            PlayerDAO.setP(aux);
        }
        return result;
    }

    public void loginFX() {
        String user = Fuser.getText();
        String pass = DigestUtils.sha512Hex(Fpass.getText());
        if (!user.isEmpty() && !pass.isEmpty()) {
            if (login(user, pass)) {
                try {
                    App.setRoot("menu");
                } catch (IOException ex) {
                    Logger.getLogger(startController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                showWarning("Error", "Inicio de sesion", "Error al iniciar sesión, compruebe los datos");
            }

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
