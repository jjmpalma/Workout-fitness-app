/*
 * @(#) ExerciseOverviewController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

import uk.ac.aber.cs221.group10.app.Main;
import uk.ac.aber.cs221.group10.app.WorkoutRoutinesConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class to display an overview from the exercise chosen.
 *
 * @author Isaac Ladds
 * @author Juan Manuel Palma
 * @author Keean Griffith
 * @version 1.1 Initial Development
 */

public class ExerciseOverviewController implements Initializable {

   WorkoutRoutinesConfig[] x;

   @FXML
   private Label noOfExercises;
   @FXML
   private Label workingTime;
   @FXML
   private Label restingTime;
   @FXML
   private Label midwayRest;

   /**
    * Converts a given integer representing second into a string as minutes and seconds
    *
    * @param sec time to pass in.
    * @return returns time as seconds.
    */
   public String sToTime(int sec) {
      int second = sec % 60;
      int minute = sec / 60;
      return minute + ":" + (second < 10 ? "0" + second : second);
   }

   /**
    * Button functionality to load the workout page
    *
    * @param actionEvent Workout Start button is pressed.
    * @throws IOException if fxml file cannot be found.
    */
   public void goToWorkout(javafx.event.ActionEvent actionEvent) throws IOException {

      Parent workoutParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("workout.fxml")));
      Scene workoutScene = new Scene(workoutParent);
      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(workoutScene);
      window.show();
   }

   /**
    * Initialises the Exercise Overview UI
    *
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      x = Main.getWorkoutRoutines();
      noOfExercises.setText(Integer.toString(Main.getWorkoutRoutines()[Main.getWorkoutChosen()].getExercises()));
      workingTime.setText(sToTime(Main.getWorkoutRoutines()[Main.getWorkoutChosen()].getWorkTime()));
      restingTime.setText(sToTime(Main.getWorkoutRoutines()[Main.getWorkoutChosen()].getRestTime()));
      midwayRest.setText(sToTime(Main.getWorkoutRoutines()[Main.getWorkoutChosen()].getMidRestTime()));
   }

   /**
    * Button functionality to load either the preconfigured workouts page or the custom workouts page
    *
    * @param actionEvent when back button is pressed.
    * @throws IOException if file cannot be found.
    */
   public void previousSelection(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent homeParent;
      if (Main.isPreviousP()) {
         homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("preconfiguredWorkouts.fxml")));
      } else {
         homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customWorkout.fxml")));

      }
      Scene homeScene = new Scene(homeParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(homeScene);
      window.show();
   }
}
