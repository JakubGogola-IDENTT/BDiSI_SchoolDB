package ZwiazekNauczycielstwaPolskiego.DeletingData;

import ZwiazekNauczycielstwaPolskiego.GUI;
import ZwiazekNauczycielstwaPolskiego.LoginWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class DeleteSubjectWindow extends DeleteWindow {


    @FXML
    private TextField subjectsName;

    @FXML
    private TextField classCode;

    @FXML
    private Label label;

    public DeleteSubjectWindow () {
        super();
    }

    @FXML
    private void deleteSubjectFromDatabaseHandler (MouseEvent mouseEvent) {
        query = "CALL deleteSubject('" + subjectsName.getText() +
                "','" + classCode.getText() + "')";
        try {
            label.setText("");
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            subjectsName.clear();
            classCode.clear();
        } catch (SQLException ex) {
            label.setText("Nie można usunać przedmiotu!");
        }
    }

    @FXML
    @Override
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        GUI.getPrimaryStage().setScene(LoginWindow.getScene());
    }

}
