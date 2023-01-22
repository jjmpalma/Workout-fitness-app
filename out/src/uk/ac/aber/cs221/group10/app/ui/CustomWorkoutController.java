/*
 * @(#) MCustomWorkoutController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

import uk.ac.aber.cs221.group10.app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A class that allows the user to create their own custom workout.
 *
 * @author Isaac Ladds
 * @author Keean Griffith
 *
 * @version 1.1 Initial Development
 */

public class CustomWorkoutController implements Initializable {

   @FXML
   Slider noOfExercisesSlider;
   @FXML
   Slider workTimeSlider;
   @FXML
   Slider restTimeSlider;
   @FXML
   Slider midRestTimeSlider;
   @FXML
   Label noOfExercisesLabel;
   @FXML
   Label workTimeLabel;
   @FXML
   Label restTimeLabel;
   @FXML
   Label midRestTimeLabel;


   /**
    * Method used to initialize the Controller.
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      noOfExercisesSlider.setValue( Main.getWorkoutRoutines()[8].getExercises());
      noOfExercisesLabel.textProperty().setValue(String.valueOf((int)noOfExercisesSlider.getValue()));
      workTimeSlider.setValue( Main.getWorkoutRoutines()[8].getWorkTime());
      workTimeLabel.textProperty().setValue(String.valueOf(sToTime((int)workTimeSlider.getValue())));
      restTimeSlider.setValue( Main.getWorkoutRoutines()[8].getRestTime());
      restTimeLabel.textProperty().setValue(String.valueOf(sToTime((int)restTimeSlider.getValue())));
      midRestTimeSlider.setValue( Main.getWorkoutRoutines()[8].getMidRestTime());
      midRestTimeLabel.textProperty().setValue(String.valueOf(sToTime((int)midRestTimeSlider.getValue())));
      noOfExercisesSlider.valueProperty().addListener((observableValue, number, t1) -> noOfExercisesLabel.textProperty().setValue(
              String.valueOf(t1.intValue())));
      workTimeSlider.valueProperty().addListener((observableValue, number, t1) -> workTimeLabel.textProperty().setValue(sToTime(t1.intValue())));
      restTimeSlider.valueProperty().addListener((observableValue, number, t1) -> restTimeLabel.textProperty().setValue(sToTime(t1.intValue())));
      midRestTimeSlider.valueProperty().addListener((observableValue, number, t1) -> midRestTimeLabel.textProperty().setValue(sToTime(t1.intValue())));
   }

   /**
    * Method to go to the exercise overview scene.
    * @param actionEvent When go to exercise button is pressed.
    * @throws IOException if file cannot be found.
    */
   @FXML
   public void goToExerciseOverview(javafx.event.ActionEvent actionEvent) throws IOException {
      Main.setWorkoutChosen(8);
      Main.getWorkoutRoutines()[8].setExercises((int)noOfExercisesSlider.getValue());
      Main.getWorkoutRoutines()[8].setWorkTime((int)workTimeSlider.getValue());
      Main.getWorkoutRoutines()[8].setMidRestTime((int)midRestTimeSlider.getValue());
      Main.getWorkoutRoutines()[8].setRestTime((int)restTimeSlider.getValue());

      Parent exerciseOverviewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseOverview.fxml")));
      Scene exerciseOverviewScene = new Scene(exerciseOverviewParent);
      Main.setPreviousP(false);
      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(exerciseOverviewScene);
      window.show();
   }

   /**
    * Method to go back to the previous scene.
    * @param actionEvent When back button is pushed.
    * @throws IOException if file can not be found.
    */
   public void backButton(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
      Scene homeScene = new Scene(homeParent);

      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(homeScene);
      window.show();
   }

   /**
    * Method to return seconds and minutes and seconds.
    * @param sec Seconds input.
    * @return As minutes and seconds.
    */
   public String sToTime(int sec){
      int second = sec %60;
      int minute = sec / 60;
      return minute + ":" + (second < 10 ? "0" + second : second);
   }


}
