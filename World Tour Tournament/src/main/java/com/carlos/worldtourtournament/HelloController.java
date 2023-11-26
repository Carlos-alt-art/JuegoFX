package com.carlos.worldtourtournament;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    @FXML
    private Label myLabel;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(getClass().getResource("/intro_tekken_street.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setAutoPlay(true);


        Pane pane = (Pane) mediaView.getParent();
        pane.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            mediaView.setVisible(false);
            myLabel.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view-modo-seleccion.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) pane.getScene().getWindow(); // Asegúrate de obtener el Stage de la escena actual
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        mediaPlayer.setOnEndOfMedia(() -> {
            mediaView.setVisible(false);
            myLabel.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view-modo-seleccion.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mediaView.fitWidthProperty().bind(pane.widthProperty());
        mediaView.fitHeightProperty().bind(pane.heightProperty());
    }
}
