/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.PlayerDAO;
import com.miguel.proyectojava.model.ScoreDAO;
import com.miguel.proyectojava.utils.Utils;
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

    public void signup() {
        String name = Fname.getText();
        String lastname = Flastname.getText();
        String email = Femail.getText();
        String user = Fuser.getText();
        String pass = DigestUtils.sha512Hex(Fpass.getText());

        if (!name.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pass.isEmpty()) {
            if (Utils.validateEmail(email)) {
                if (PlayerDAO.isAvailablePlayer(user)) {
                    showWarning("Error", "Usuario ya registrado", "Por favor, introduce otro nombre de usuario");
                } else {
                    if (PlayerDAO.signup(name, lastname, email, user, pass) && ScoreDAO.createScore(user)) {
                        showInformation();
                    } else {
                        showWarning("Error", "No se ha registrado", "No se ha podido registrar el usuario, intentelo de nuevo");
                    }
                }
            } else {
                showWarning("Error", "Email incorrecto", "Por favor, compruebe que has introducido correctamente el email");
            }
        } else {
            showWarning("Error", "Datos incorrectos", "Por favor, compruebe que has introducido correctamente los datos");
        }
    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public boolean showInformation() {
        boolean r = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText("Usuario registrado");
        alert.setContentText("El usuario ha sido registrado con exito, por favor inicie sesion");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            r = true;
            try {
                App.setRoot("start");
            } catch (IOException ex) {
                Logger.getLogger(createPlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            r = false;
        }
        return r;

    }

    public void back() throws IOException {
        App.setRoot("start");
    }
}
