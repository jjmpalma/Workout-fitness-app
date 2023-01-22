/*
 * @(#) HomeController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

import uk.ac.aber.cs221.group10.app.WorkoutRead;
import uk.ac.aber.cs221.group10.app.WorkoutHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class to display the home screen ui
 *
 * @author Isaac Ladds
 * @author Keean Griffith
 * @author Juan Manuel Palma
 *
 * @version 1.1 Initial Development
 */

public class HomeController implements Initializable {


   @FXML
   private TableView<WorkoutHistory> historyTable;
   @FXML
   private TableColumn<WorkoutHistory, String> dateColumn;
   @FXML
   private TableColumn<WorkoutHistory, String> exercisesColumn;
   @FXML
   private TableColumn<WorkoutHistory, String> workTimeColumn;
   @FXML
   private TableColumn<WorkoutHistory, String> restTimeColumn;
   @FXML
   private TableColumn<WorkoutHistory, String> midRestTimeColumn;

   /**
    * Button functionality to load the preconfigured workouts page
    *
    * @param actionEvent when button is pressed
    * @throws IOException throws if file not found
    */
   public void goToPreconfigured(javafx.event.ActionEvent actionEvent) throws IOException {

      Parent preconfiguredParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("preconfiguredWorkouts.fxml")));
      Scene preconfiguredScene = new Scene(preconfiguredParent);
      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(preconfiguredScene);
      window.show();
   }

   /**
    * Button functionality to load the custom workouts page
    *
    * @param actionEvent When Create Custom workout button is pressed
    * @throws IOException throws if file not found
    */
   public void goToCustom(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent customParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customWorkout.fxml")));
      Scene customScene = new Scene(customParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(customScene);
      window.show();

   }

   /**
    * Button functionality to load the workout history page
    *
    * @param actionEvent when button is pressed
    * @throws IOException throws if file not found
    */
   public void goToWorkoutHistory(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent customParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("workoutHistory.fxml")));
      Scene customScene = new Scene(customParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(customScene);
      window.show();

   }

   /**
    * Initialises the home Controller UI
    *
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

      //Associate data with columns
      dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateColumn"));
      exercisesColumn.setCellValueFactory(new PropertyValueFactory<>("exercisesColumn"));
      workTimeColumn.setCellValueFactory(new PropertyValueFactory<>("workTimeColumn"));
      restTimeColumn.setCellValueFactory(new PropertyValueFactory<>("restTimeColumn"));
      midRestTimeColumn.setCellValueFactory(new PropertyValueFactory<>("midRestTimeColumn"));

      //Load data to table
      historyTable.setItems(getTableData());

      historyTable.setEditable(true);

      dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
   }

   /**
    * Generates a list of workout histories to be read into java fx tables
    *
    * @return list of all workouts
    */
   public ObservableList<WorkoutHistory> getTableData() {

      ObservableList<WorkoutHistory> tableData = FXCollections.observableArrayList();

      WorkoutRead wor = new WorkoutRead("src/uk/ac/aber/cs221/group10/app/data/History.xml");

      LinkedList<WorkoutHistory> records = wor.getRecords();

      for (int i = 0; i < 3 && i < records.size(); i++) {
         tableData.add(records.get(i));
      }

      return tableData;
   }

}

