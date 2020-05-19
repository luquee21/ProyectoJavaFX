/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.model.Champion;
import com.miguel.proyectojava.model.ChampionDAO;
import com.miguel.proyectojava.model.PlayerDAO;
import com.miguel.proyectojava.model.Skill;
import com.miguel.proyectojava.model.SkillDAO;
import com.miguel.proyectojava.utils.Utils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author migue
 */
public class chooseChampionAndSkillController implements Initializable {

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

    @FXML
    private TableView<Skill> tableskill;

    @FXML
    private TableColumn<Skill, String> tskill;

    @FXML
    private TableColumn<Skill, String> tdescription;

    @FXML
    private TableColumn<Skill, Float> modifier;

    private ObservableList<Skill> dataskill;

    private ObservableList<Champion> data;

    private menuController parent;

    private Object params;
    private Stage myStage;

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(menuController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }
    List<Skill> aux2;
    List<Champion> aux;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Champion
        this.data = FXCollections.observableArrayList();
        aux = ChampionDAO.selectAll();
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

        //Skill
        this.dataskill = FXCollections.observableArrayList();
        aux2 = SkillDAO.selectAll();
        int randomskill = Utils.randomIA(aux2);
        SkillDAO.setSkillIA(aux2.get(randomskill));
        dataskill.addAll(aux2);

        this.tskill.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSkill());
        });

        this.tdescription.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });

        this.modifier.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getModifier());
        });
        tableskill.setItems(dataskill);
    }

    public void selectSkill() {
        Skill selected = tableskill.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!parent.showConfirm("Seleccionar", "A punto de seleccionar", "Desea seleccionar la habilidad " + selected.getSkill())) {
                return;
            }
            SkillDAO.setSkill(selected);

        } else {
            //LISTO
            parent.showWarning("¡Ojo!", "Ninguna habilidad seleccionada", "Seleccione una habilidad antes de presionar elegir");

        }
    }

    public void startMatch() {
        if (SkillDAO.getSkill() != null && PlayerDAO.getP() != null) {
            parent.play.setDisable(false);
            this.myStage.close();
        } else {
            parent.showWarning("¡Ojo!", "Seleccionar", "Seleccione un campeon y habilidad antes de empezar");
        }
    }

    public void selectChampion() {
        Champion selected = table.getSelectionModel().getSelectedItem();

        if (selected != null) {
            if (!parent.showConfirm("Seleccionar", "A punto de seleccionar", "Desea seleccionar al campeon " + selected.getName())) {
                return;
            }
            ChampionDAO.setChampion(selected);
            
            //Escoge campeon diferente a la IA al que ha elegido el jugador
            aux.remove(selected);
            int random = Utils.randomIA(aux);
            ChampionDAO.setChampionIA(aux.get(random));

        } else {
            //LISTO
            parent.showWarning("¡Ojo!", "Ningún campeon seleccionado", "Seleccione un campeon antes de presionar elegir");
        }

    }

}
