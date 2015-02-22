package com.alexandragurova.address;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;

import java.io.File;

/**
 * Created by Gurova on 18.02.2015.
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {

    //Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty addressbook.
     */
    @FXML
    private void handleNew(){
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        //Display file chooser on top of the primary stage
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is showm.
     */
    @FXML
    private void handleSave(){
        File personFile = mainApp.getPersonFilePath();

        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter to ".xml"
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "(*.xml)");
        fileChooser.getExtensionFilters().add(extensionFilter);

        //Display file chooser on top of the primary stage
        //Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout(){
        Dialogs.create()
                .title("AddressApp")
                .masthead("About")
                .message("Address sample application from Marco Jakob tutorial.")
                .showInformation();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit(){
        System.exit(0);
    }


    @FXML
    private void hadleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
}
