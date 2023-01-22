/*
 * @(#) WorkoutController.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app.ui;

import javafx.scene.media.AudioClip;
import uk.ac.aber.cs221.group10.app.Main;
import uk.ac.aber.cs221.group10.app.Exercise;
import uk.ac.aber.cs221.group10.app.WorkoutRoutinesConfig;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.nio.file.Paths;
import java.util.*;
import java.net.URL;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.ac.aber.cs221.group10.app.WorkoutStorage;

import java.io.File;
import java.io.IOException;

/**
 * Class to run the workout.
 *
 * @author Isaac Ladds
 * @author Keean Griffith
 *
 * @version 1.1 Initial Development
 */

public class WorkoutController implements Initializable {

   @FXML
   private Button startStop;
   @FXML
   private VBox colorBox;
   @FXML
   private Button videoButton;
   @FXML
   private Label timer;
   @FXML
   private MediaView mv;
   @FXML
   private Label currentLabel;
   @FXML
   private Label exerciseTitle;
   @FXML
   private Label exerciseDescription;

   private  int exerciseNumber =1;
   private boolean hadMidRest =false;
   private float midRest;
   int workoutState = 3; //0=exercise 1=rest 2=mid-rest 3=finished
   int warmUpsDone = 0;
   private String[] warmups;
   boolean coolDownStarted = false;
   int warmupAndCooldownTime = 30;
   private AudioClip beep;
   WorkoutStorage wos;
   String warmupAndCooldownText;
   List<Exercise> shuffledList; //Holds exercises in shuffled array
   WorkoutRoutinesConfig workoutRoutineConfig;
   public static int[] exercisesIDs;
   private boolean hasStartedExercise = false;Si
   private boolean workoutPaused = false;
   private boolean videoPlaying = false;
   private Timeline timeline;
   private final SimpleIntegerProperty timeSeconds = new SimpleIntegerProperty();
   public int startTime;
   private MediaPlayer mp;
   private Media me;
   /**
    * Method to initialize the Controller.
    * @param url The location used to resolve relative paths for the root object, or null if the location is not known
    * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
    */
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      //randomising list of exercises
      workoutRoutineConfig  = Main.getWorkoutRoutines()[Main.getWorkoutChosen()];
      shuffledList = Arrays.asList(Main.getExercises());
      Collections.shuffle(shuffledList);
      exercisesIDs = new int[workoutRoutineConfig.getExercises()];
      warmups = new String[12];
      //loads the warmup file paths
      warmups[0] = "src/uk/ac/aber/cs221/group10/media/HamstringStretch.mp4";
      warmups[1] = "src/uk/ac/aber/cs221/group10/media/QuadStretch.mp4";
      warmups[2] = "src/uk/ac/aber/cs221/group10/media/GroinStretch.mp4";
      warmups[3] = "src/uk/ac/aber/cs221/group10/media/AcrossBodyTricepStretch.mp4";
      warmups[4] = "src/uk/ac/aber/cs221/group10/media/BehindTheHeadTricepStretch.mp4";
      warmups[5] = "src/uk/ac/aber/cs221/group10/media/CalfStretch.mp4";
      warmups[6] = "Hamstring Stretch";
      warmups[7] = "Quad Stretch";
      warmups[8] = "Groin Stretch";
      warmups[9] = "Across Body Tricep Stretch";
      warmups[10] = "Behind The head Tricep Stretch";
      warmups[11] = "Calf Stretch";


      for (int i = 0; i < workoutRoutineConfig.getExercises(); i++){
         exercisesIDs[i] = shuffledList.get(i).getExerciseID();
      }
      exerciseDescription.setWrapText(true);
      warmupAndCooldownText = "Hamstring Stretch - Straighten your leg and put your heel on the ground, then push down slowly on just above your knee and you'll feel the stretch in your hamstring.\nQuad Stretch - Lift your leg behind you and grab your foot with your hand, pull your foot towards your glutes slowly and you'll feel the stretch.\nGroin Stretch - Lean to the side and keep your one leg straight, slowly lower with the non straight leg and you should feel a stretch in the groin.\nCross Body Tricep Stretch - Bring your arm across your body and use your other arm to pull it toward yourself, you should feel a stretch in your rear delts and tricep.\nBehind The Head Tricep Stretch - Lift your hand behind your head, then with your other arm grab your elbow and slowly pull, you'll feel a stretch in your tricep and lats.\nCalf Stretch - Lean with your hands against a wall, place your foot quite far back and keep your whole foot on the ground. Push off the wall and put all your weight on one foot, it should feel a strong stretch in your calf.";

      exerciseDescription.setText(warmupAndCooldownText);
      //saving info to file
      wos = new WorkoutStorage("src/uk/ac/aber/cs221/group10/app/data/History.xml");
      wos.newRecord(Main.getWorkoutRoutines()[Main.getWorkoutChosen()], exercisesIDs);

      // reading in timing variables
      currentLabel.setText("Ready to Start");
      midRest = workoutRoutineConfig.getExercises();
      midRest = (int) Math.ceil((double)midRest / 2);
      timeSeconds.set(warmupAndCooldownTime);
      timer.textProperty().bind(timeSeconds.asString());
      //loading beep path
      beep = new AudioClip(Paths.get("src/uk/ac/aber/cs221/group10/media/beep-01a.mp3").toUri().toString());
      warmupAndCooldown();
   }

   //

   /**
    * Method to load path to media for warmups and cooldowns.
    */
   public void warmupAndCooldown () {
      String path = warmups[warmUpsDone];
      me = new Media(new File(path).toURI().toString());
      mp = new MediaPlayer(me);
      mv.setMediaPlayer(mp);
      exerciseTitle.setText(warmups[warmUpsDone+6] + " (" + (warmUpsDone+1) +"/" + 6 + ")");
   }

   // loads path to media for exercises

   /**
    * Method to load path to media for exercises.
    */
   public void newExercise () {
      String path = (shuffledList.get(exerciseNumber-1).getExerciseVideoFile()).getAbsolutePath();
      me = new Media(new File(path).toURI().toString());
      mp = new MediaPlayer(me);
      mv.setMediaPlayer(mp);
      exerciseDescription.setText(shuffledList.get(exerciseNumber-1).getDescription());
      exerciseTitle.setText(shuffledList.get(exerciseNumber-1).getExerciseName() + " (" + exerciseNumber +"/" + workoutRoutineConfig.getExercises() + ")");
   }

   // runs the workout structure

   /**
    * Method used to play the correct part of the workout routine.
    */
   public void workoutRunner() {
      hasStartedExercise=false;
      if (exerciseNumber > workoutRoutineConfig.getExercises() && !coolDownStarted) {
         coolDownStarted = true;
         warmUpsDone=0;
         workoutState = 4;
      }

      switch (workoutState) {
         case 0 -> {
            workoutState = 1;
            colorBox.setStyle("-fx-background-color: #c3da15;");
            mp.stop();
            videoPlaying = false;
            newExercise();
            videoButton.setText("Play Video");
            currentLabel.setText("Rest");
            beep.play(0.2);
            startTime();
         }
         case 1 -> {
            workoutState = 0;
            wos.setQuantity(exerciseNumber);
            colorBox.setStyle("-fx-background-color: #26bf42;");
            currentLabel.setText("Exercise");
            beep.play(0.2);
            startTime();
         }
         case 2 -> {
            currentLabel.setText("Workout Over");
            mp.stop();
         }
         case 3 -> { //warmup
            currentLabel.setText("Warmup");
            if (mp != null) {
               mp.stop();
            }
            videoButton.setText("Play Video");
            videoPlaying = false;
            warmupAndCooldown();
            startTime();
         }
         case 4 -> { //coolDown
            exerciseDescription.setText(warmupAndCooldownText);
            currentLabel.setText("Cooldown");
            mp.stop();
            videoButton.setText("Play Video");
            videoPlaying = false;
            warmupAndCooldown();
            startTime();
         }
      }
   }

   /**
    * Method used to display the timer for the workout.
    */
   public void startTime() {
      if (!hasStartedExercise) {
         hasStartedExercise=true;
         startStop.setText("Pause workout");

         switch (workoutState) {
            case 0:
               currentLabel.setText("Exercise");
               exerciseNumber++;
               startTime = workoutRoutineConfig.getWorkTime();
               break;
            case 1:
               if (exerciseNumber > midRest && !hadMidRest && workoutRoutineConfig.getMidRestTime() >= 10) {
                  hadMidRest=true;
                  currentLabel.setText("Mid Workout Rest");
                  startTime =workoutRoutineConfig.getMidRestTime();
               } else {
                  startTime = workoutRoutineConfig.getRestTime();
               }
               break;
            case 2:
               break;
            case 3:
               warmUpsDone++;
               if (warmUpsDone >= 6) {
                  workoutState = 0;
               }
               startTime=warmupAndCooldownTime;
               break;
            case 4:
               warmUpsDone++;
               if (warmUpsDone >= 6) {
                  workoutState = 2;
               }
               startTime=warmupAndCooldownTime;
               break;
         }

         timeSeconds.set(startTime);
         timeline = new Timeline();
         timeline.getKeyFrames().add(
                 new KeyFrame(Duration.seconds(startTime + 1),
                         new KeyValue(timeSeconds, 0)));

         timeline.playFromStart();
         timeline.setOnFinished(((finish) -> workoutRunner()));

      }
      else {
         if (!workoutPaused) {
            timeline.pause();
            colorBox.setStyle("-fx-background-color: #bc3838;");
            workoutPaused = true;
            startStop.setText("Play workout");

         }
         else {
            timeline.play();
            switch (workoutState) {
               case 0:
               case 3:
               case 4:
                  colorBox.setStyle("-fx-background-color: #26bf42;");
                  break;
               case 1:
                  colorBox.setStyle("-fx-background-color: #c3da15;");
                  break;
               case 2:
                  break;
            }
            workoutPaused = false;
            startStop.setText("Pause workout");

         }
      }
   }

   /**
    * Method use to go back to the home screen.
    * @param actionEvent When quit workout button is pressed.
    * @throws IOException throws if fxml file not found
    */
   public void goBackHome(javafx.event.ActionEvent actionEvent) throws IOException {
      Parent homeParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
      Scene homeScene = new Scene(homeParent);
      mp.stop();
      if (timeline != null) {
         timeline.stop();
      }
      Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      window.setScene(homeScene);
      window.show();
   }

   /**
    * Method to play and pause the exercise video.
    */
   public void playVideo() {
      if (!videoPlaying) {
         videoPlaying = true;
         videoButton.setText("Pause Video");
         mp.play();
         mp.setCycleCount(100);
      }
      else {
         videoPlaying = false;
         videoButton.setText("Play Video");
         mp.pause();
      }
   }
}
