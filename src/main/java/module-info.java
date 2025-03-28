module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task2 to javafx.fxml;
    exports com.example.task2;
    //exports model;
    //opens model to javafx.fxml;
    exports model.memento;
    opens model.memento to javafx.fxml;
    exports model.factory;
    opens model.factory to javafx.fxml;
    exports model.shapes;
    opens model.shapes to javafx.fxml;
}