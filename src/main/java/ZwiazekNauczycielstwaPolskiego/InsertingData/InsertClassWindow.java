package ZwiazekNauczycielstwaPolskiego.InsertingData;

import ZwiazekNauczycielstwaPolskiego.DBConnector;
import ZwiazekNauczycielstwaPolskiego.GUI;
import ZwiazekNauczycielstwaPolskiego.LoginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertClassWindow {

    private DBConnector dbConnector;

    private Connection connection;

    private PreparedStatement statement;

    private String query;

    @FXML
    private TextField className;

    @FXML
    private TextField classTutor;

    @FXML
    private Label label;

    public InsertClassWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    public void addClassToDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL addClass ('" + className.getText() + "','" +
                classTutor.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dodać klasy!");
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    private void clearTextFields () {
        className.clear();
        classTutor.clear();
    }
}
