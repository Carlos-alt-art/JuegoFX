package com.carlos.worldtourtournament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ControllerSeleccionPersonaje {

    @FXML
    private Button invisibleButtonReady;

    @FXML
    private ImageView ready;

    @FXML
    private ImageView jugador1;

    @FXML
    private ImageView jugador2;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button invisibleButton1;

    @FXML
    private Button invisibleButton2;

    @FXML
    private Button invisibleButton3;

    @FXML
    private Button invisibleButton4;

    @FXML
    private Button invisibleButton5;

    @FXML
    private Button invisibleButton6;

    @FXML
    private ImageView fotoHeihachi;

    @FXML
    private ImageView fotoKazuya;

    @FXML
    private ImageView fotoPaul;

    @FXML
    private ImageView fotoBlanka;

    @FXML
    private ImageView fotoRyu;

    @FXML
    private ImageView fotoChun;

    @FXML
    private Pane pane;
    private String selectedImagePath1;
    private String selectedImagePath2;

    private int jugadoresSeleccionados = 0;

    @FXML
    private Button invisibleButtonReadySceneChange;

    public void initialize() {
        String mediaPath = getClass().getResource("/fondo_seleccion_personaje.mp4").toExternalForm();
        Media media = new Media(mediaPath);

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(1150);
        mediaView.setFitHeight(647);

        mediaPlayer.play();

        setButtonCursor(invisibleButton1);
        setButtonCursor(invisibleButton2);
        setButtonCursor(invisibleButton3);
        setButtonCursor(invisibleButton4);
        setButtonCursor(invisibleButton5);
        setButtonCursor(invisibleButton6);
        setButtonCursor(invisibleButtonReady);


        invisibleButtonReady.setOnAction(this::handleInvisibleButtonReady);
        invisibleButton1.setOnAction(this::handleInvisibleButton1Click);
        invisibleButton2.setOnAction(this::handleInvisibleButton2Click);
        invisibleButton3.setOnAction(this::handleInvisibleButton3Click);
        invisibleButton4.setOnAction(this::handleInvisibleButton4Click);
        invisibleButton5.setOnAction(this::handleInvisibleButton5Click);
        invisibleButton6.setOnAction(this::handleInvisibleButton6Click);




    }

    private void handleInvisibleButtonReady(ActionEvent event) {
        if (jugadoresSeleccionados == 2) {
            Personaje jugador1 = new Personaje(selectedImagePath1);
            Personaje jugador2 = new Personaje(selectedImagePath2);

            String readyImagePath = "/ready.png";
            mostrarImagen(readyImagePath, ready);

            changeScene("batalla-view.fxml");
        } else {
            showAlert("Antes de Continuar", "Selecciona a ambos jugadores antes de continuar.");
        }
    }


    private void changeScene(String nombreFXML) {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource(nombreFXML));
            Parent root = cargador.load();

            ControllerBatalla controllerBatalla = cargador.getController();

            controllerBatalla.setPlayers(new Personaje(selectedImagePath1), new Personaje(selectedImagePath2));

            Scene escena = new Scene(root);

            Stage stage = (Stage) invisibleButtonReady.getScene().getWindow();
            stage.setScene(escena);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void setButtonCursor(Button button) {
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
    }

    private void handleInvisibleButton1Click(ActionEvent event) {
        handleInvisibleButtonClick("/heihachi.gif", "/heihachi2.gif");
    }

    private void handleInvisibleButton2Click(ActionEvent event) {
        handleInvisibleButtonClick("/kazuya.gif", "/kazuya2.gif");
    }

    private void handleInvisibleButton3Click(ActionEvent event) {
        handleInvisibleButtonClick("/paul.gif", "/paul2.gif");
    }

    private void handleInvisibleButton4Click(ActionEvent event) {
        handleInvisibleButtonClick("/blanka.gif", "/blanka2.gif");
    }

    private void handleInvisibleButton5Click(ActionEvent event) {
        handleInvisibleButtonClick("/ryu.gif", "/ryu2.gif");
    }

    private void handleInvisibleButton6Click(ActionEvent event) {
        handleInvisibleButtonClick("/chun.gif", "/chun2.gif");
    }

    private void handleInvisibleButtonClick(String imagePath1, String imagePath2) {
        if (jugador1.getImage() == null) {
            selectedImagePath1 = imagePath1;
            mostrarImagen(selectedImagePath1, jugador1);
            jugadoresSeleccionados++;
        } else if (jugador2.getImage() == null) {
            selectedImagePath2 = imagePath2;
            mostrarImagen(selectedImagePath2, jugador2);
            jugadoresSeleccionados++;
        }

        if (jugadoresSeleccionados == 2) {
            String readyImagePath = "/ready.png";
            mostrarImagen(readyImagePath, ready);
        }
    }

    private void mostrarImagen(String imagePath, ImageView targetImageView) {
        String imageUrl = getClass().getResource(imagePath).toExternalForm();
        Image image = new Image(imageUrl);

        targetImageView.setImage(image);
        targetImageView.setPreserveRatio(true);
        targetImageView.setFitWidth(140);
        targetImageView.setFitHeight(140);
    }
}
