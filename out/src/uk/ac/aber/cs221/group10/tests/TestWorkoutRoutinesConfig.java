/*
       * @(#) TestWorkoutRoutinesConfig.java 1.0 2021/04/30
       *
       * Copyright (c) 2021 Aberystwyth University.
       * All rights reserved.
       *
       */

package uk.ac.aber.cs221.group10.tests;

import uk.ac.aber.cs221.group10.app.ExerciseAndWorkouts;
import uk.ac.aber.cs221.group10.app.WorkoutRoutinesConfig;

import org.w3c.dom.NodeList;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TestWorkoutRoutinesConfig.java - A test class, tests the methods inside the class WorkoutRoutinesConfig.java.
 * Used in TestRunner.java.
 *
 * @author  Bartek S [bas22]
 * @author  Ahmed Z [ahz1]
 * @version 1.0 Initial Development.
 * @see  uk.ac.aber.cs221.group10.app.WorkoutRoutinesConfig
 * @see  TestRunner
 */

public class TestWorkoutRoutinesConfig {

   // Initialises class to be tested on
   ExerciseAndWorkouts wor = new ExerciseAndWorkouts("src/uk/ac/aber/cs221/group10/app/data/TestData.xml");
   NodeList nlwo = wor.getWorkout();
   WorkoutRoutinesConfig testWorkoutConfig = new WorkoutRoutinesConfig(nlwo.item(0));

   /**
    * Tests values generated in constructor are assigned properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkoutRoutinesConfigConstructor() {
      int valueCheck = testWorkoutConfig.getExercises(); // Taking direct value instead of get() method because it's not tested yet
      assertTrue(valueCheck >= 2 &&  valueCheck <= 30, "Fail! ExerciseCount is not in Range");

      valueCheck = testWorkoutConfig.getWorkTime();
      assertTrue(valueCheck >= 10 && valueCheck <= 300, "Fail! WorkoutTime is not in Range");

      valueCheck = testWorkoutConfig.getRestTime();
      assertTrue(valueCheck >= 10 && valueCheck <= 300, "Fail! RestTime is not in Range");

      valueCheck = testWorkoutConfig.getMidRestTime();
      assertTrue(valueCheck >= 10 && valueCheck <=300, "Fail! MidRestTime is not in Range");

      assertTrue(testWorkoutConfig.getWorkoutID() > -1, "Fail! WorkoutID is not an Int or not in Range");
   }

   /**
    * Tests get method for exercises works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigGetExercises(){
      assertNotEquals((Double) null, testWorkoutConfig.getExercises());

      Assertions.assertEquals(20, testWorkoutConfig.getExercises());
   }

   /**
    * Tests set method for exercises works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigSetExercises(){
      testWorkoutConfig.setExercises(10);
      Assertions.assertEquals(10, testWorkoutConfig.getExercises());
   }

   /**
    * Tests get method for work time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigGetWorkTime(){
      assertNotEquals((Double) null, testWorkoutConfig.getWorkTime());

      Assertions.assertEquals(60, testWorkoutConfig.getWorkTime());
   }

   /**
    * Tests set method for work time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigSetWorkTime() {
      testWorkoutConfig.setWorkTime(10);
      Assertions.assertEquals(10, testWorkoutConfig.getWorkTime());
   }

   /**
    * Tests get method for rest time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigGetRestTime(){
      assertNotEquals((Double) null, testWorkoutConfig.getRestTime());

      Assertions.assertEquals(30, testWorkoutConfig.getRestTime());
   }

   /**
    * Tests set method for rest time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigSetRestTime() {
      testWorkoutConfig.setRestTime(10);
      Assertions.assertEquals(10, testWorkoutConfig.getRestTime());
   }

   /**
    * Tests get method for get mid rest time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigGetMidRestTime(){
      assertNotEquals((Double) null, testWorkoutConfig.getMidRestTime());

      Assertions.assertEquals(60, testWorkoutConfig.getMidRestTime());
   }

   /**
    * Tests set method for mid rest time works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigSetMidRestTime() {
      testWorkoutConfig.setMidRestTime(10);
      Assertions.assertEquals(10, testWorkoutConfig.getMidRestTime());
   }

   /**
    * Tests get method for workout ID works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigGetWorkoutID(){
      assertNotEquals((Double) null, testWorkoutConfig.getWorkoutID());

      Assertions.assertEquals(1, testWorkoutConfig.getWorkoutID());
   }

   /**
    * Tests set method for set workout ID works properly.
    * @see WorkoutRoutinesConfig
    */
   @Test
   public void WorkRoutineConfigSetWorkoutID(){
      testWorkoutConfig.setWorkoutID(10);
      Assertions.assertEquals(10, testWorkoutConfig.getWorkoutID());
   }
}