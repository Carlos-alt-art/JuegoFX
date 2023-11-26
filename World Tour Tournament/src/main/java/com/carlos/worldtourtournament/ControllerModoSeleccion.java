package com.carlos.worldtourtournament;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ControllerModoSeleccion {
    @FXML
    private MediaView mediaView;

    @FXML
    private Pane rootPane;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;



    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    public void initialize() {
        String videoResourcePath = "/fondoSeleccion.mp4";
        Media media = new Media(getClass().getResource(videoResourcePath).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        Platform.runLater(() -> {
            Stage stage = (Stage) mediaView.getScene().getWindow();

            double width = stage.getWidth();
            double height = stage.getHeight();

            mediaView.setFitWidth(width);
            mediaView.setFitHeight(height);
        });

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        configureFadeTransition(imageView1);

        configureFadeTransition(imageView2);



        configureInvisibleButton(button1, imageView1);
        configureInvisibleButton(button2, imageView2);

    }

    private void configureFadeTransition(ImageView imageView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), imageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.play();
    }

    private void configureInvisibleButton(Button button, ImageView imageView) {
        button.setOpacity(0);
        button.setMinSize(imageView.getFitWidth(), imageView.getFitHeight());
        button.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            button.getScene().setCursor(Cursor.HAND);
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            button.getScene().setCursor(Cursor.DEFAULT);
        });

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (button == button1) {
                showAlert("Próximamente", "En la temporada dos estará disponible el modo historia.");
            } else if (button == button2) {
                changeScene("view-seleccion-personaje.fxml");
            }
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.initStyle(StageStyle.UNDECORATED);

        alertStage.getScene().getWindow().hide();

        alert.showAndWait();
    }

    private void changeScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) button2.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
