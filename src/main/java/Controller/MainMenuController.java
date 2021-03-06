package Controller;

import View.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    public Button duelButton;

    @FXML
    public Button profileButton;

    @FXML
    public Button scoreBoardButton;

    @FXML
    public Button shopButton;

    @FXML
    public Button deckButton;

    @FXML
    public Button loginButton;

    @FXML
    public Button importAndExportBtn;

    @FXML
    public Button createCardBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                LoginAndSignUpView loginAndSignUpView = new LoginAndSignUpView();
                try {
                    loginAndSignUpView.loginShow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        duelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage mainMenuStage = (Stage) duelButton.getScene().getWindow();
                mainMenuStage.close();
                URL url = null;
                try {
                    url = new File("src/main/java/FXMLFiles/ChooseDuel.fxml").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Parent root = null;
                try {
                    assert url != null;
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("chooseDuel");
                assert root != null;
                stage.setScene(new Scene(root, 1376, 666));
                stage.show();
            }
        });
        shopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                ShopView shopView = ShopView.getInstance();
                try {
                    shopView.start(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        deckButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) deckButton.getScene().getWindow();
                DeckController deckController = DeckController.getInstance();
                try {
                    deckController.showScene(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                URL url = null;
                try {
                    url = new File("src/main/java/FXMLFiles/profileMenu.fxml").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Parent root = null;
                try {
                    assert url != null;
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage mainMenuStage = MainMenu.stage;
                mainMenuStage.setTitle("MainMenu");
                assert root != null;
                mainMenuStage.setScene(new Scene(root, 1920, 1000));
                mainMenuStage.show();
                ProfileView.initializeLBL();
            }
        });

        scoreBoardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) scoreBoardButton.getScene().getWindow();
                ScoreBoardController scoreBoardController = ScoreBoardController.getInstance();
                try {
                    scoreBoardController.showScoreBoard(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        importAndExportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) importAndExportBtn.getScene().getWindow();
                try {
                    ImportAndExportController.getInstance().showScene(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        createCardBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) createCardBTN.getScene().getWindow();
                URL url = null;
                try {
                    url = new File("src/main/java/FXMLFiles/CreateCard.fxml").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Parent root = null;
                try {
                    assert url != null;
                    root = FXMLLoader.load(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("CreateCard");
                assert root != null;
                stage.setScene(new Scene(root, 1920, 1150));
                stage.show();
            }
        });
    }
}
