/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.controller;

import com.miguel.proyectojava.App;
import com.miguel.proyectojava.model.Champion;
import com.miguel.proyectojava.model.ChampionDAO;
import com.miguel.proyectojava.model.Match;
import com.miguel.proyectojava.model.MatchDAO;
import com.miguel.proyectojava.model.Player;
import com.miguel.proyectojava.model.PlayerDAO;
import com.miguel.proyectojava.model.Score;
import com.miguel.proyectojava.model.ScoreDAO;
import com.miguel.proyectojava.model.Skill;
import com.miguel.proyectojava.model.SkillDAO;
import com.miguel.proyectojava.utils.ConnectionUtil;
import com.miguel.proyectojava.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    //TAB-Match
    @FXML
    private TextArea matchresult;

    @FXML
    private TextArea textwinner;

    @FXML
    private Label vs;

    @FXML
    public Button play;

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

        //TAB Match
        //Versus
        vs.setText(PlayerDAO.getP().getUsername() + " vs " + PlayerDAO.getIA().getUsername());

    }

    /**
     * Funcion que cierra sesion y vuelve a la pestaña de login
     *
     * @throws IOException
     */
    public void signoff() throws IOException {
        App.setRoot("start");
        PlayerDAO.setP(null);
      
    }

    /**
     * Funcion para recargar la pagina
     *
     * @throws IOException
     */
    public void recharge() throws IOException {
        App.setRoot("menu");
    }

    /**
     * Funcion que cierra el programa
     */
    public void exit() {
        try {
            ConnectionUtil.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.exit();
        System.exit(0);
    }

    /**
     * Funcion que borra el jugador que se le pasa como parametro, en este caso el jugador que inicia sesion
     *
     * @param p Jugador que se va a borrar
     * @return devuelve cierto si ha conseguido borrar al jugador
     */
    public boolean deletePlayer(Player p) {
        boolean result = false;
        PlayerDAO dao = new PlayerDAO(p);
        if (dao.delete()) {
            result = true;
        }
        return result;
    }

    /**
     * Funcion asociada con javafx que recoge los datos necesarios para realizar
     * la partida
     */
    public void matchFX() {
        Player player1 = PlayerDAO.getP();
        Player player2 = PlayerDAO.getIA();
        Champion champion1 = ChampionDAO.getChampion();
        Champion champion2 = ChampionDAO.getChampionIA();
        Skill skill1 = SkillDAO.getSkill();
        Skill skill2 = SkillDAO.getSkillIA();
        champion1.setSkill(skill1);
        champion2.setSkill(skill2);

        //result
        if (player1 == null || player2 == null || champion1 == null || champion2 == null || skill1 == null || skill2 == null) {
            showWarning("Error", "Partida", "Error al realizar la partida");
        } else {
            List<Match> resume = match(player1, player2, champion1, champion2, skill1, skill2);
            if (resume.isEmpty()) {
                showWarning("Error", "Partida", "No se ha podido realizar la partida");
            } else {
                for (Match c : resume) {
                    matchresult.setText("El jugador: " + c.getPlayer().getUsername() + " ha jugado con el personaje " + c.getcPlayer().getName() + " con la habilidad " + c.getSkill_champion_player().getSkill() + "\n"
                            + "Ha realizado " + c.getcPlayer().getN_attacks() + " ataques, ha fallado " + c.getcPlayer().getMiss_attack() + " ataques y se ha quedado a " + c.getcPlayer().getHealth() + " de vida\n"
                            + "La IA ha jugado con el personaje " + c.getcIA().getName() + " con la habilidad " + c.getSkill_champion_IA().getSkill() + "\n"
                            + "Ha realizado " + c.getcIA().getN_attacks() + " ataques, ha fallado " + c.getcIA().getMiss_attack() + " ataques y se ha quedado a " + c.getcIA().getHealth() + " de vida\n");

                    textwinner.setText("Ha ganado " + c.getWinner());

                }
            }
        }

        play.setDisable(true);
    }

    /**
     * Funcion que recibe los datos necesarios para crear la partida, asociada
     * con matchFX
     *
     * @param player1 Jugador
     * @param player2 IA
     * @param champion1 campeon del jugador 1
     * @param champion2 campeon de la IA
     * @param skill1 habilidad del jugador 1
     * @param skill2 habilidad del jugador 2
     * @return devuelve el array con los datos de la partida
     */
    public List<Match> match(Player player1, Player player2, Champion champion1, Champion champion2, Skill skill1, Skill skill2) {
        Match m = new Match(player1, champion1, champion2, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), skill1, skill2);
        MatchDAO dao = new MatchDAO(m);
        int damage = 0, health = 0, miss1 = 0, miss2 = 0, attack1 = 0, attack2 = 0;
        boolean result = true, aux = true, aux2 = true;
        String winner = null;
        List<Match> resume = new ArrayList<>();

        while (result) {
            aux = Utils.probmiss(champion1.getProb_Miss());
            aux2 = Utils.probmiss(champion2.getProb_Miss());

            if (aux && result) {
                damage = champion1.getDamage() - champion2.getArmor();
                health = champion2.getHealth() - damage;
                if (health <= damage) {
                    champion2.setHealth(0);
                    attack1++;
                    winner = player1.getUsername();
                    dao.setWinner(winner);
                    result = false;
                } else {
                    champion2.setHealth(health);
                    attack1++;
                }
            } else if (!aux && result) {
                miss1++;
            }

            if (aux2 && result) {
                damage = champion2.getDamage() - champion1.getArmor();
                health = champion1.getHealth() - damage;
                if (health <= damage) {
                    champion1.setHealth(0);
                    attack2++;
                    winner = player2.getUsername();
                    dao.setWinner(winner);
                    result = false;
                } else {
                    champion1.setHealth(health);
                    attack2++;
                }
            } else if (!aux2 && result) {
                miss2++;
            }

        }
        champion1.setMiss_attack(miss1);
        champion1.setN_attacks(attack1);
        champion2.setMiss_attack(miss2);
        champion2.setN_attacks(attack2);
        m.setWinner(winner);
        resume.add(m);
        dao.save();
        winner(winner, player1);

        return resume;
    }

    /**
     * Funcion para saber el ganador y sumarle las victorias, derrotas y totales
     *
     * @param winner recibe el ganador
     * @param p recibe el jugador.
     */
    public void winner(String winner, Player p) {
        Score s = null;
        s = ScoreDAO.selectAllFromPlayer(p.getUsername());
        int total = 0, win = 0, defeats = 0;
        if (p.getUsername().equals(winner)) {
            total = s.getTotal_games() + 1;
            win = s.getVictories() + 1;
            defeats = s.getDefeats();

        } else {
            total = s.getTotal_games() + 1;
            defeats = s.getDefeats() + 1;
            win = s.getVictories();
        }
        ScoreDAO.update(p.getUsername(), win, defeats, total);
    }

    /**
     * Funcion asociada con javafx para recibir el jugador que se va a borrar
     */
    @FXML
    public void deletePlayerFX() {
        Player selected = ptableprofile.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm("Eliminar", "A punto de eliminar", "Desea eliminar " + selected.getUsername())) {
                return;
            }
            //BORRAR DE LA INTERFAZ
            dataprofile.remove(selected);
            //BORRAR DE LA BBDD
            if (deletePlayer(selected)) {
                try {
                    //LOGOUT
                    signoff();
                } catch (IOException ex) {
                    Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            //LISTO
            showWarning("¡Ojo!", "Ningún Item a borrar", "Seleccione un item antes de presionar delete");
        }
    }

    /**
     * Funcion que ejecuta una alerta
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
     * Funcion que ejecuta una ventana con confirmacion
     *
     * @param title recibe el titulo de la ventana
     * @param header recibe la cabecera de la ventana
     * @param text recibe la descripcion de la ventana
     * @return
     */
    public boolean showConfirm(String title, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Funcion para cambiar la contraseña del usuario
     *
     * @param user recibe el usuario
     * @param pass recibe la contraseña
     * @return
     */
    public boolean changePassword(String user, String pass) {
        boolean result = false;
        Player aux = PlayerDAO.selectAllFromPlayerLogin(user);
        if (aux != null) {
            aux.setPassword(pass);
            PlayerDAO dao = new PlayerDAO(aux);
            if (dao.save()) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Funcion asociada con javafx que recibe la contraseña que se va a
     * actualizar y su posterior llamada a changepassword
     */
    @FXML
    public void changePasswordFX() {
        if (!ptableprofiletpassword.getText().isEmpty() && !ptableprofiletrepeatpassword.getText().isEmpty()) {
            if (ptableprofiletpassword.getText().equals(ptableprofiletrepeatpassword.getText())) {
                String password = ptableprofiletpassword.getText();
                String user = PlayerDAO.getP().getUsername();
                if (changePassword(user, password)) {
                    showInformation("Exito", "Contraseña", "La contraseña ha sido cambiada");
                    ptableprofiletpassword.clear();
                    ptableprofiletrepeatpassword.clear();
                } else {
                    showWarning("Error", "Error al cambiar contraseña", "No se ha podido cambiar la contraseña");
                }
            } else {
                showWarning("Error", "Error al cambiar contraseña", "Comprueba la contraseña");
            }
        } else {
            showWarning("Error", "Error al cambiar contraseña", "Inserta los dos campos de contraseña");
        }
    }

    /**
     * Funcion que llama a la ventana de elegir campeon y habilidad
     *
     * @throws IOException
     */
    public void createMatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("chooseChampionAndSkill.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Seleccionar campeon y habilidad");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            chooseChampionAndSkillController modalController = fxmlLoader.getController();
            if (modalController != null) {
                modalController.setStage(modalStage);
                modalController.setParent(this);
                modalController.setParams(null);

            }

            modalStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funcion que ejecuta una ventana con informacion
     *
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
