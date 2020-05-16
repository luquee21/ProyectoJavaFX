/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.Skill;
import com.miguel.proyectojava.model.SkillDAO;
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
public class chooseSkillController implements Initializable {

    @FXML
    private TableView<Skill> table;

    @FXML
    private TableColumn<Skill, String> tskill;

    @FXML
    private TableColumn<Skill, String> tdescription;

    private ObservableList<Skill> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        List<Skill> aux = SkillDAO.selectAll();
        data.addAll(aux);

        this.tskill.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSkill());
        });

        this.tdescription.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });
        table.setItems(data);
    }

    public boolean showConfirm(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Eleccion de habilidad");
        alert.setContentText("¿Desea elegir la habilidad: " + title + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public void selectSkill() {
        Skill selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getSkill())) {
                return;
            }
            SkillDAO.setSkill(selected);
            try {
                App.setRoot("match");
            } catch (IOException ex) {
                Logger.getLogger(chooseSkillController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //LISTO
            showWarning("¡Ojo!", "Ninguna habilidad seleccionada", "Seleccione una habilidad antes de presionar elegir");

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
