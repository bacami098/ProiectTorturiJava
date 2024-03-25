module com.example.haiterog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires junit;

    opens com.example.haiterog to javafx.fxml;
    exports com.example.haiterog;

    exports com.example.haiterog.Teste;
}

