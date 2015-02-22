package com.alexandragurova.address;

import com.alexandragurova.address.model.Person;
import com.alexandragurova.address.util.CalendarUtil;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

import java.awt.*;

/**
 * Created by Gurova on 18.02.2015.
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private  TextField lastNameField;
    @FXML
    private  TextField streetField;
    @FXML
    private  TextField postalCodeField;
    @FXML
    private  TextField cityField;
    @FXML
    private  TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     * This method automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){

    }

    /**Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.dialogStage.getIcons().add(new Image("file:resources/images/address_book.png"));
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(String.valueOf(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(CalendarUtil.format(person.getBirthday()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user click ok.
     */
    @FXML
    private void handleOk(){
        if(isInputValid()){
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(CalendarUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user click cancel.
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }


    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input valid
     */
    private boolean isInputValid() {
        String errorMesssage = "";

        if(firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMesssage += "No valid first name!\n";
        }

        if(lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMesssage += "No valid last name!\n";
        }

        if(streetField.getText() == null || streetField.getText().length() == 0){
            errorMesssage += "No valid street!\n";
        }

        if(postalCodeField.getText() == null || postalCodeField.getText().length() == 0){
            errorMesssage += "No valid postal code!\n";
        } else {
            try {
                //try to parse the postal code into an int.
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMesssage += "No valid postal code(must be an integer)!\n";
            }
        }

        if(cityField.getText() == null || cityField.getText().length() == 0){
            errorMesssage += "No valid city!\n";
        }

        if(birthdayField.getText() == null || birthdayField.getText().length() == 0){
            errorMesssage += "No valid birthday!\n";
        } else {
            if(!CalendarUtil.validString(birthdayField.getText())){
                errorMesssage += "No valid birthday! Use the format dd.MM.yyyy.\n";
            }
        }

        if(errorMesssage.length() == 0){
            return true;
        } else {
            Dialogs.create()
                    .title("Invalid input")
                    .masthead("Please correct invalid fields.")
                    .message(errorMesssage)
                    .showError();
            return false;
        }
    }
}
