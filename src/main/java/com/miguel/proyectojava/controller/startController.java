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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    /**
     * Funcion que comprueba los datos del usuario para ver si puede iniciar
     * sesion
     *
     * @param user recibe el usuario
     * @param pass recibe la contraseña
     * @return devuelve cierto si los datos de sesion son correctos con la base
     * de datos
     */
    private boolean login(String user, String pass) {
        boolean result = false;
        Player aux = PlayerDAO.selectAllFromPlayerLogin(user);
        Player IA = PlayerDAO.selectAllFromPlayerLogin("IA");
        if (aux != null && aux.getPassword().equals(pass)) {
            PlayerDAO.setP(aux);
            PlayerDAO.setIA(IA);
            result = true;
        }
        return result;
    }

    /**
     * Funcion asociada con javafx que recibe los datos de sesion del usuario
     * para su posterior comprobacion con la funcion login
     */
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

        } else {
            showWarning("Error", "Inicio de sesion", "Error al iniciar sesión, los campos estan vacios");
        }
    }

    /**
     * Funcion que cambia de ventana a crer jugador
     *
     * @throws IOException
     */
    public void signup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("createPlayer.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Registro");
            modalStage.initModality(Modality.APPLICATION_MODAL);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            createPlayerController modalController = fxmlLoader.getController();
            if (modalController != null) {
                modalController.setStage(modalStage);
                modalController.setParent(this);
                modalController.setParams(null);

            }

            modalStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(startController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funcion que ejecuta una ventana con una advertencia
     *
     * @param title recibe el titulo de la ventana
     * @param header recibe la cabecera de la ventana
     * @param description recibe la descripcion de la ventana
     */
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    /**
     * Funcion que ejecuta una ventana de informacion
     * @param title recibe el titulo de la ventana
     * @param header recibe la cabecera de la ventana
     * @param content recibe la descripcion de la ventana
     * @return
     */
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

}
