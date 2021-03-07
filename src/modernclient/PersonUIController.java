package modernclient;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modernclient.model.Person;
import modernclient.model.SampleData;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonUIController implements Initializable {

    @FXML private TextField firstnameTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextArea notesTextArea;
    @FXML private DatePicker dateArea;
    @FXML private Button removeButton;
    @FXML private Button createButton;
    @FXML private Button updateButton;
    @FXML private ListView<Person> listView;
    @FXML private MenuItem menuExit;
    @FXML private MenuItem menuAbout;
    @FXML private ComboBox gender;


    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);

    private Person selectedPerson;
    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private ChangeListener<Person> personChangeListener;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable the Remove/Edit buttons if nothing is selected in the ListView control
        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
            .or(modifiedProperty.not()).or(firstnameTextField.textProperty().isEmpty().or(lastnameTextField.textProperty().isEmpty())
            ));
        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNotNull()
            .or(firstnameTextField.textProperty().isEmpty().or(lastnameTextField.textProperty().isEmpty())));

        SampleData.fillSampleData(personList);

        // Use a sorted list; sort by lastname; then by firstname
        SortedList<Person> sortedList = new SortedList<>(personList);

        // sort by lastname first, then by firstname; ignore notes
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
            if (result == 0) {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result;
        });

        listView.setItems(sortedList);

        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener = (observable, oldValue, newValue) -> {
            System.out.println("Selected item: " + newValue);
            // newValue can be null if nothing is selected
            selectedPerson = newValue;

            // Boolean property modifiedProperty tracks whether the user has changed any of the
            //three text controls in the form. We reset this flag after each ListView selection and use
            //this property in a bind expression to control the Update button’s disable property.


            if (newValue != null) {
                // Populate controls with selected Person
                firstnameTextField.setText(selectedPerson.getFirstname());
                lastnameTextField.setText(selectedPerson.getLastname());
                notesTextArea.setText(selectedPerson.getNotes());
                dateArea.setValue(selectedPerson.getBirthDate());
                gender.setValue(selectedPerson.getGender());
            } else {
                firstnameTextField.setText("");
                lastnameTextField.setText("");
                notesTextArea.setText("");
                dateArea.setValue(null);
            }
            modifiedProperty.set(false);
        });

        // Pre-select the first item
        listView.getSelectionModel().selectFirst();

        menuExit.setOnAction((ActionEvent t) -> System.exit(0));
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));

        menuAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Stage popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("About Us");
                Label aboutText = new Label();
                aboutText.setText("Simple application to manage data of people.\n" +
                                  "Created on 7th March 2021 by Iskender Berdiev.\n" +
                                  "Email: iskender.berdiev@gmail.com\nPhone number: +996772849020");
                VBox layout = new VBox(10);
                layout.getChildren().addAll(aboutText);
                layout.setAlignment(Pos.CENTER);
                Scene scene = new Scene(layout);
                popupWindow.setScene(scene);
                popupWindow.showAndWait();
            }
        });
    }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {
        modifiedProperty.set(true);
    }
    @FXML
    private void handleDateAndGenderUpdate() {
        modifiedProperty.set(true);
    }

    @FXML
    private void createButtonAction(ActionEvent actionEvent) {
        System.out.println("Create");
        Person person = new Person(firstnameTextField.getText(), lastnameTextField.getText(), notesTextArea.getText(), dateArea.getValue(), (String) gender.getValue());
        personList.add(person);
        // and select it
        listView.getSelectionModel().select(person);
    }

    @FXML
    private void removeButtonAction(ActionEvent actionEvent) {
        System.out.println("Remove " + selectedPerson);
        personList.remove(selectedPerson);
    }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent) {
        System.out.println("Update " + selectedPerson);
        Person p = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
        p.setFirstname(firstnameTextField.getText());
        p.setLastname(lastnameTextField.getText());
        p.setNotes(notesTextArea.getText());
        p.setBirthDate(dateArea.getValue());
        p.setGender((String) gender.getValue());
        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
        modifiedProperty.set(false);
    }



}