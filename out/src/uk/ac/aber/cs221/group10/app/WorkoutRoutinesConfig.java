/*
 * @(#) WorkoutRoutinesConfig.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.Node;

/**
 * This class is used to represent workouts and associated values.
 *
 * @author Keean Griffith
 *
 * @version 1.1 Initial Development
 */


enum Intensity {
   BEGINNER,
   ADVANCED,
   LIIT,
   HIIT,
   CUSTOM
}

public class WorkoutRoutinesConfig {

   private int exerciseCount;

   private int workTime;

   private int restTime;

   private int midRestTime;

   private Intensity intensity;

   private int workoutID;

   /**
    * Constructor, takes a node to populate instance variables.
    * @author Alex Clive
    * @param node the node to use to populate variables.
    */
   public WorkoutRoutinesConfig(Node node){
      this.exerciseCount = Integer.parseInt(node.getAttributes().getNamedItem("quantity").getNodeValue());
      this.workTime = Integer.parseInt(node.getAttributes().getNamedItem("work").getNodeValue());
      this.restTime = Integer.parseInt(node.getAttributes().getNamedItem("rest").getNodeValue());
      this.midRestTime = Integer.parseInt(node.getAttributes().getNamedItem("midrest").getNodeValue());
      this.workoutID = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
      this.intensity = Intensity.valueOf(node.getAttributes().getNamedItem("intensity").getNodeValue());
   }

   /**
    * Old Constructor
    * @deprecated
    * Now unused. (Was used for testing early on in development.)
    * @param exerciseCount the intended amount of exercises to do.
    * @param workTime the time spent on each exercise.
    * @param restTime the time between each exercise.
    * @param midRestTime the time between the middle two exercises.
    * @param workoutID the ID of this configuration of timings.
    * @param intensity the intended intensity of the workouts.
    */
   public WorkoutRoutinesConfig(int exerciseCount, int workTime, int restTime, int midRestTime, int workoutID, Intensity intensity) {
      this.exerciseCount = exerciseCount;
      this.workTime = workTime;
      this.restTime = restTime;
      this.midRestTime = midRestTime;
      this.workoutID = workoutID;
      this.intensity = intensity;
   }

   /**
    * A toString method used for test printing.
    * @return a full routine represented as a string.
    */
   @Override
   public String toString(){
      return exerciseCount + " Exercises, " + workTime + " Secs Work, " + restTime + " Secs rest, " + midRestTime + " Min Midway rest";
   }

   public int getExercises() {
      return exerciseCount;
   }

   public void setExercises(int exercises) {
      this.exerciseCount = exercises;
   }

   public int getWorkTime() {
      return workTime;
   }

   public void setWorkTime(int workTime) {
      this.workTime = workTime;
   }

   public int getRestTime() {
      return restTime;
   }

   public void setRestTime(int restTime) {
      this.restTime = restTime;
   }

   public int getMidRestTime() {
      return midRestTime;
   }

   public void setMidRestTime(int midRestTime) {
      this.midRestTime = midRestTime;
   }

   public Intensity getIntensity() {
      return intensity;
   }

   public void setIntensity(Intensity intensity) {
      this.intensity = intensity;
   }

   public int getWorkoutID() {
      return workoutID;
   }

   public void setWorkoutID(int workoutID) {
      this.workoutID = workoutID;
   }
}