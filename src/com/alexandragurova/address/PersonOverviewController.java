package com.alexandragurova.address;

import com.alexandragurova.address.model.Person;
import com.alexandragurova.address.util.CalendarUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;

/**
 * Created by Gurova on 18.02.2015.
 */
public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label street;
    @FXML
    private Label postalCode;
    @FXML
    private Label city;
    @FXML
    private Label birthday;

    private MainApp mainApp;

    /**
     * The construtor is called before the initialize method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class.
     * This method automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){
        //Initialize the person Table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //Reset person details
        showPersonDetails(null);

        //Listen for selection changes
        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable,
                                Person oldValue, Person newValue) {
                showPersonDetails(newValue);
            }
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        //Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person){
        if(person != null){
            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
            street.setText(person.getStreet());
            postalCode.setText(String.valueOf(person.getPostalCode()));
            city.setText(person.getCity());
            birthday.setText(CalendarUtil.format(person.getBirthday()));
        } else {
            firstName.setText("");
            lastName.setText("");
            street.setText("");
            postalCode.setText("");
            city.setText("");
            birthday.setText("");
        }
    }

    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    public void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonDialog(tempPerson);
        if(okClicked){
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    public void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            //Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }
}
