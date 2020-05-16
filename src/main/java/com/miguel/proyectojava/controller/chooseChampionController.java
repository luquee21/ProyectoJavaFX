/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;


import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.Champion;
import com.miguel.proyectojava.model.ChampionDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author migue
 */
public class chooseChampionController implements Initializable {

    @FXML
    private TableView<Champion> table;

    @FXML
    private TableColumn<Champion, String> tchampion;

    @FXML
    private TableColumn<Champion, Integer> tdamage;

    @FXML
    private TableColumn<Champion, Integer> thealth;

    @FXML
    private TableColumn<Champion, Integer> tarmor;

    @FXML
    private TableColumn<Champion, Integer> tmiss;

    private ObservableList<Champion> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        List<Champion> aux = ChampionDAO.selectAll();
        data.addAll(aux);

        this.tchampion.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });

        this.tdamage.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDamage());
        });

        this.thealth.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getHealth());
        });

        this.tarmor.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getArmor());
        });

        this.tmiss.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getProb_Miss());
        });

        table.setItems(data);
    }

    public void selectChampion() {
        Champion selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getName())) {
                return;
            }
            ChampionDAO.setChampion(selected);
            try {
                App.setRoot("chooseSkill");
            } catch (IOException ex) {
                Logger.getLogger(chooseChampionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //LISTO
            showWarning("¡Ojo!", "Ningún campeon seleccionado", "Seleccione un campeon antes de presionar elegir");
        }
    }

    public boolean showConfirm(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Eleccion de campeón");
        alert.setContentText("¿Desea elegir el campeon: " +title + " ?");

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

}
