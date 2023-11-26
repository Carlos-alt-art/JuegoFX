module com.carlos.worldtourtournament {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.carlos.worldtourtournament to javafx.fxml;
    exports com.carlos.worldtourtournament;
}