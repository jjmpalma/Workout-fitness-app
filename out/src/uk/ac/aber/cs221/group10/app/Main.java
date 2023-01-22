/*
 * @(#) Main.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.Objects;

/**
 * Initialises the app.
 * It serves as a bridge between the classes modelling workouts and exercises, the Persistent storage and the UI classes.
 *
 * @author Isaac Ladds
 * @author Juan Manuel Palma
 *
 * @version 1.1 Initial Development
 */

public class Main extends Application {

   //Javafx variables
   public Stage window;
   Scene home;

   //Workout and exercise variables
   private static WorkoutRoutinesConfig[] workoutRoutines;
   private static Exercise[] exercises;
   private static boolean previousP;
   private static int workoutChosen;

   /**
    * Initialises UI
    *
    * @param primaryStage Stage for the UI
    * @exception  IOException throws if file not found
    */
   @Override
   public void start(Stage primaryStage) throws IOException {
      window = primaryStage;
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ui/home.fxml")));
      home = new Scene(root);
      window.setTitle("group 10 workout app");
      window.setScene(home);
      window.show();

   }

   /**
    * Initialises the app logic
    *
    * @param args
    */
   public static void main(String[] args) {

      ExerciseAndWorkouts loader = new ExerciseAndWorkouts("src/uk/ac/aber/cs221/group10/app/data/Data.xml");
      NodeList nlworkouts = loader.getWorkout();
      workoutRoutines = new WorkoutRoutinesConfig[nlworkouts.getLength()];
      for (int i = 0; i < nlworkouts.getLength(); i++) {
         workoutRoutines[i] = new WorkoutRoutinesConfig(nlworkouts.item(i));
         //System.out.println(workoutRoutines[i].toString());
      }

      NodeList nlexercises = loader.getExercise();
      exercises = new Exercise[nlexercises.getLength()];
      for (int i = 0; i < nlexercises.getLength(); i++) {
         exercises[i] = new Exercise(nlexercises.item(i));
         //System.out.println(exercises[i].exerciseName);
      }
      launch(args);
   }

   //Read Write properties
   public static boolean isPreviousP() {
      return previousP;
   }

   public static void setPreviousP(boolean previousP) {
      Main.previousP = previousP;
   }

   public static int getWorkoutChosen() {
      return workoutChosen;
   }

   public static void setWorkoutChosen(int workoutChosen) {
      Main.workoutChosen = workoutChosen;
   }

   public static Exercise[] getExercises() {
      return exercises;
   }

   public static void setExercises(Exercise[] exercises) {
      Main.exercises = exercises;
   }

   //Read only properties
   public static WorkoutRoutinesConfig[] getWorkoutRoutines() {
      return workoutRoutines;
   }
}