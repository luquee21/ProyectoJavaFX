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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author migue
 */
public class startController{

    @FXML
    private TextField Fuser;

    @FXML
    private PasswordField Fpass;
    
    
    

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
        if(modalController!=null){
            modalController.setStage(modalStage);
            modalController.setParent(this);
            modalController.setParams(null);
            
        }
        
        modalStage.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(startController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

   

}
