/*
 * @(#) ExerciseAndWorkout.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.NodeList;


/**
 * A class designed to read the designated data file.
 *
 * @author Alex Clive
 *
 * @version 1.1 Initial Development
 */

public class  ExerciseAndWorkouts extends XMLio{

   public ExerciseAndWorkouts(String filename){
      super(filename);
   }

   /**
    * getter for nodelist of exercise nodes
    * @return nodelist of exercise nodes
    */
   public NodeList getExercise() {
      return super.XpathQuery("/data/exercises/exercise");
   }

   /**
    * getter for nodelist of workout nodes
    * @return nodelist of workout nodes
    */
   public NodeList getWorkout() {
      return super.XpathQuery("/data/preconfig/*/workout");
   }
}