/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.Match;
import com.miguel.proyectojava.model.MatchDAO;
import com.miguel.proyectojava.model.Player;
import com.miguel.proyectojava.model.PlayerDAO;
import com.miguel.proyectojava.model.Score;
import com.miguel.proyectojava.model.ScoreDAO;
import com.miguel.proyectojava.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author migue
 */
public class menuController implements Initializable {

    //TAB profile  
    @FXML
    private TableView<Player> ptableprofile;
    @FXML
    private TableColumn<Player, String> ptableprofiletuser;
    @FXML
    private TableColumn<Player, String> ptableprofiletname;
    @FXML
    private TableColumn<Player, String> ptableprofiletlastname;
    @FXML
    private TableColumn<Player, String> ptableprofiletemail;
    @FXML
    private PasswordField ptableprofiletpassword;
    @FXML
    private PasswordField ptableprofiletrepeatpassword;
    @FXML
    private Label login;

    private ObservableList<Player> dataprofile;

    //TAB record-score
    @FXML
    private TableView<Score> ptablescore;

    @FXML
    private TableColumn<Score, String> ptablerecordscoretuser;
    @FXML
    private TableColumn<Score, Integer> ptablerecordscoretvictories;
    @FXML
    private TableColumn<Score, Integer> ptablerecordscoretdefeats;
    @FXML
    private TableColumn<Score, Integer> ptablerecordscorettotalgames;

    private ObservableList<Score> datarecordscore;

    //TAB record-matches
    @FXML
    private TableView<Match> ptablematches;

    @FXML
    private TableColumn<Match, Integer> ptablematchestmatch;

    @FXML
    private TableColumn<Match, String> ptablematchestuser;

    @FXML
    private TableColumn<Match, String> ptablematchestchampion;

    @FXML
    private TableColumn<Match, String> ptablematchestskill;

    @FXML
    private TableColumn<Match, String> ptablematchestchampionia;

    @FXML
    private TableColumn<Match, String> ptablematchestskillia;

    @FXML
    private TableColumn<Match, String> ptablematchestwinner;

    @FXML
    private TableColumn<Match, String> ptablematchestdate;

    @FXML
    private TableColumn<Match, String> ptablematchesttime;

    private ObservableList<Match> datarecordmatches;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //PESTAÑA PERFIL
        this.dataprofile = FXCollections.observableArrayList();
        Player aux = PlayerDAO.getP();
        dataprofile.add(aux);
        this.login.setText("Has iniciado sesion como: " + aux.getUsername());

        this.ptableprofiletuser.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getUsername());
        });

        this.ptableprofiletname.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });

        this.ptableprofiletlastname.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getLastname());
        });

        this.ptableprofiletemail.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getEmail());
        });

        //EDITABLES     
        ptableprofiletname.setCellFactory(TextFieldTableCell.forTableColumn());
        ptableprofiletname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {

                Player selected = (Player) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setName(t.getNewValue());  //<<- update lista en vista

                //update en mysql
                PlayerDAO dao = new PlayerDAO(selected);
                dao.save();

            }
        }
        );

        ptableprofiletlastname.setCellFactory(TextFieldTableCell.forTableColumn());
        ptableprofiletlastname.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {

                Player selected = (Player) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setLastname(t.getNewValue());  //<<- update lista en vista

                //update en mysql
                PlayerDAO dao = new PlayerDAO(selected);
                dao.save();

            }
        }
        );
        ptableprofiletemail.setCellFactory(TextFieldTableCell.forTableColumn());
        ptableprofiletemail.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {

                Player selected = (Player) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setEmail(t.getNewValue());  //<<- update lista en vista
                if (Utils.validateEmail(selected.getEmail())) {
                    //update en mysql
                    PlayerDAO dao = new PlayerDAO(selected);
                    dao.save();
                } else {
                    showWarning("Error", "Error al actualizar", "Introduce un email valido");
                }

            }
        }
        );

        ptableprofile.setEditable(true);
        ptableprofile.setItems(dataprofile);

        //TAB RECORD-SCORE
        this.datarecordscore = FXCollections.observableArrayList();
        List<Score> ascore = ScoreDAO.selectAll();
        datarecordscore.addAll(ascore);

        this.ptablerecordscoretuser.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getUsername_player());
        });
        this.ptablerecordscoretvictories.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getVictories());
        });
        this.ptablerecordscoretdefeats.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDefeats());
        });
        this.ptablerecordscorettotalgames.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getTotal_games());
        });

        ptablescore.setItems(datarecordscore);

        //TAB RECORD-MATCHES
        this.datarecordmatches = FXCollections.observableArrayList();
        List<Match> amatch = MatchDAO.selectAll(PlayerDAO.getP().getUsername());
        datarecordmatches.addAll(amatch);

        this.ptablematchestmatch.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getId());
        });

        this.ptablematchestuser.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getPlayer().getUsername());
        });

        this.ptablematchestchampion.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getcPlayer().getName());
        });

        this.ptablematchestskill.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSkill_champion_player().getSkill());
        });
        this.ptablematchestchampionia.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getcIA().getName());
        });
        this.ptablematchestskillia.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSkill_champion_IA().getSkill());
        });
        this.ptablematchestwinner.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getWinner());
        });
        this.ptablematchestdate.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDate().toString());
        });
        this.ptablematchesttime.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getTime().toString());
        });
        ptablematches.setItems(datarecordmatches);
    }

    public void signoff() throws IOException {
        //vuelvo a la clase base
        App.setRoot("start");
        //seteo a nulo el usuario por si inicia sesion con otro.
        PlayerDAO.setP(null);
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void deletePlayer() {
        Player selected = ptableprofile.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getUsername())) {
                return;
            }
            //BORRAR DE LA INTERFAZ
            dataprofile.remove(selected);
            //BORRAR DE LA BBDD
            PlayerDAO dao = new PlayerDAO(selected);
            dao.delete();
            try {
                //LOGOUT
                signoff();
            } catch (IOException ex) {
                Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //LISTO
            showWarning("¡Ojo!", "Ningún Item a borrar", "Seleccione un item antes de presionar delete");
        }
    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public boolean showConfirm(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("A punto de eliminar");
        alert.setContentText("Desea borrar al usuario " + title + "\n Se borrara tanto el usuario como sus partidas y su puntuacion");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public void changePassword() {
        if (!ptableprofiletpassword.getText().isEmpty() && !ptableprofiletrepeatpassword.getText().isEmpty()) {
            if (ptableprofiletpassword.getText().equals(ptableprofiletrepeatpassword.getText())) {
                String password = ptableprofiletpassword.getText();
                Player selected = PlayerDAO.selectAllFromPlayerLogin(PlayerDAO.getP().getUsername());
                selected.setPassword(password);
                PlayerDAO dao = new PlayerDAO(selected);
                dao.save();
                ptableprofiletpassword.clear();
                ptableprofiletrepeatpassword.clear();
            } else {
                showWarning("Error", "Error al cambiar contraseña", "Comprueba la contraseña");
            }
        } else {
            showWarning("Error", "Error al cambiar contraseña", "Inserta los dos campos de contraseña");
        }
    }
    
    public void startMatch() throws IOException{
        App.setRoot("chooseChampion");
    }

}
