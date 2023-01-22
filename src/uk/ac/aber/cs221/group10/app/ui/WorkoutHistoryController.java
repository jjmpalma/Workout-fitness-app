/*
 * @(#) WorkoutHistoryController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

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
import uk.ac.aber.cs221.group10.app.WorkoutRead;
import uk.ac.aber.cs221.group10.app.WorkoutHistory;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class to display the workout history.
 *
 * @author Juan Manuel Palma
 * @author Keean Griffith
 *
 * @version 1.1 Initial Development
 */

public class WorkoutHistoryController implements Initializable {

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
    * Back home page button functionality.
    *
    * @param actionEvent when back button pressed
    * @throws IOException throws if file not found
    */
   public void backButton(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
      Scene homeScene = new Scene(homeParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(homeScene);
      window.show();
   }

   /**
    * Initialises the Workout History UI.
    *
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle){
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
    * Permits access to the list holding the workout history data needed to populate the Table View element.
    *
    * @return list of workoutHistory objects.
    */
   public ObservableList<WorkoutHistory> getTableData() {

      ObservableList<WorkoutHistory> tableData = FXCollections.observableArrayList();

      WorkoutRead wor = new WorkoutRead("src/uk/ac/aber/cs221/group10/app/data/History.xml");

      LinkedList<WorkoutHistory> records = wor.getRecords();


      tableData.addAll(records);

      return tableData;
   }
}
