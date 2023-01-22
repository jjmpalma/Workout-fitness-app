/*
 * @(#) PreconfiguredWorkoutsController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

import uk.ac.aber.cs221.group10.app.Main;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class to allow to user to select preconfigured workouts.
 *
 * @author Isaac Ladds
 * @author Keean Griffith
 *
 * @version 1.1 Initial Development
 */


public class PreconfiguredWorkoutsController implements Initializable {

   @FXML
   private ChoiceBox <String>workouts;

   ObservableList list = FXCollections.observableArrayList();

   /**
    *
    * @param actionEvent When the confirm workout button is pressed.
    * @throws IOException if fxml file is not found.
    */
   @FXML
   public void confirmWorkout(javafx.event.ActionEvent actionEvent) throws IOException {
      if (workouts.getValue() != null) {
         setWorkoutChosen();
         Parent exerciseOverviewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseOverview.fxml")));
         Scene exerciseOverviewScene = new Scene(exerciseOverviewParent);
         Main.setPreviousP(true);
         Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
         window.setScene(exerciseOverviewScene);
         window.show();
      }
   }

   /**
    * Returns to the previous screen.
    * @param actionEvent When back button is pressed.
    * @throws IOException if fxml file is not found.
    */
   public void backButton(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
      Scene homeScene = new Scene(homeParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(homeScene);
      window.show();
   }

   /**
    * Initiates the scene.
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      loadData();
   }

   private void setWorkoutChosen() {
      String workout = workouts.getValue();

      for (int i = 0; i < Main.getWorkoutRoutines().length; i++) {
         if (workout.equals(Main.getWorkoutRoutines()[i].toString())){
            Main.setWorkoutChosen(i);

         }
      }
   }

   /**
    * Loads the descriptions for the preconfigured workouts.
    */
   private void loadData() {
      list.removeAll(list);
      for (int i = 0; i < Main.getWorkoutRoutines().length; i++) {
         list.add(Main.getWorkoutRoutines()[i].toString());
      }
      workouts.getItems().addAll(list);
   }
}
