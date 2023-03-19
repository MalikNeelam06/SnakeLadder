module com.example.snakeladdergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladdergame to javafx.fxml;
    exports com.example.snakeladdergame;
}