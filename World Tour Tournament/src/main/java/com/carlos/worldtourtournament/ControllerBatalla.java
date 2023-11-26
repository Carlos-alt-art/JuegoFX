package com.carlos.worldtourtournament;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerBatalla implements Initializable {

    @FXML
    private ProgressBar progressjugador1;

    @FXML
    private ProgressBar progressjugador2;

    @FXML
    private ImageView imageJugador1;

    @FXML
    private ImageView imageJugador2;

    @FXML
    private ImageView atacar1;

    @FXML
    private ImageView atacar2;

    private Personaje jugador1;
    private Personaje jugador2;

    private boolean turnoJugador1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String atacarImageURL = getClass().getResource("/atacar.png").toExternalForm();
        Image atacarImage = new Image(atacarImageURL);
        atacar1.setImage(atacarImage);

        Image atacarImage2 = new Image(getClass().getResource("/atacar.png").toExternalForm());
        atacar2.setImage(atacarImage2);
    }

    public void setPlayers(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;

        turnoJugador1 = new Random().nextBoolean();

        setImage(imageJugador1, jugador1.getImagen());
        setImage(imageJugador2, jugador2.getImagen());

        updateProgressBar(progressjugador1, jugador1.getVidaProgress());
        updateProgressBar(progressjugador2, jugador2.getVidaProgress());

        atacar1.setVisible(turnoJugador1);
        atacar2.setVisible(!turnoJugador1);
    }

    @FXML
    private void atacar1Clicked() {
        if (turnoJugador1) {
            atacar(jugador1, jugador2, progressjugador2);
            turnoJugador1 = false;
            atacar1.setDisable(true);
            atacar2.setDisable(false);
            atacar1.setVisible(false);
            atacar2.setVisible(true);
        }
    }

    @FXML
    private void atacar2Clicked() {
        if (!turnoJugador1) {
            atacar(jugador2, jugador1, progressjugador1);
            turnoJugador1 = true;
            atacar1.setDisable(false);
            atacar2.setDisable(true);
            atacar1.setVisible(true);
            atacar2.setVisible(false);
        }
    }

    private void setImage(ImageView imageView, String rutaImagen) {
        String urlImagen = getClass().getResource(rutaImagen).toExternalForm();
        Image imagen = new Image(urlImagen);
        imageView.setImage(imagen);
    }

    private void updateProgressBar(ProgressBar progressBar, double vidaProgress) {
        progressBar.setProgress(vidaProgress);

        if (vidaProgress < 0.3) {
            progressBar.setStyle("-fx-accent: red;");
        } else if (vidaProgress < 0.7) {
            progressBar.setStyle("-fx-accent: yellow;");
        } else {
            progressBar.setStyle("-fx-accent: green;");
        }
    }

    private void atacar(Personaje atacante, Personaje objetivo, ProgressBar progressBar) {
        int ataque = atacante.generarAtaqueAleatorio();
        int nuevaVida = objetivo.getVida() - ataque;
        if (nuevaVida < 0) {
            nuevaVida = 0;
        }
        objetivo.setVida(nuevaVida);

        updateProgressBar(progressBar, objetivo.getVidaProgress());

        if (nuevaVida == 0) {
            mostrarAlerta("¡Ganaste!", "Enhorabuena has ganado!!!!!!");
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);

        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icon.png"));

        alerta.showAndWait();

        Stage primaryStage = (Stage) imageJugador1.getScene().getWindow();
        primaryStage.close();
    }

}
