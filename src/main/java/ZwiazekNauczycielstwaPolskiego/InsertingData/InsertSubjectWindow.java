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

public class InsertSubjectWindow {

    private DBConnector dbConnector;

    private Connection connection;

    private PreparedStatement statement;

    private String query;

    @FXML
    private TextField subjectsName;

    @FXML
    private TextField className;

    @FXML
    private TextField teachersCode;

    @FXML
    private TextField numberOfHours;

    @FXML
    private Label label;

    public InsertSubjectWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    private void addSubjectToDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL addSubject ('" + subjectsName.getText() + "','" +
                className.getText() + "','" + teachersCode.getText() +
                "','" + numberOfHours.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            clearTextFields();
        } catch (SQLException ex) {
            label.setText("Nie można dodać przedmiotu!");
        }
    }

    @FXML
    public void backToStartingWindowHandler (ActionEvent actionEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

    private void clearTextFields () {
        subjectsName.clear();
        className.clear();
        teachersCode.clear();
        numberOfHours.clear();
    }
}
