/*
       * @(#) TestExercise.java 1.0 2021/04/30
       *
       * Copyright (c) 2021 Aberystwyth University.
       * All rights reserved.
       *
       */

package uk.ac.aber.cs221.group10.tests;

import org.w3c.dom.NodeList;
import uk.ac.aber.cs221.group10.app.Exercise;

import java.io.File;

import org.junit.Test;
import uk.ac.aber.cs221.group10.app.ExerciseAndWorkouts;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TestExercise.java - A test class, tests the methods inside the class Exercise.java.
 * Used in TestRunner.java.
 *
 * @author  Bartek S [bas22]
 * @author  Ahmed Z [ahz1]
 * @version 1.0 Initial Development.
 * @see  uk.ac.aber.cs221.group10.app.Exercise
 * @see  TestRunner
 */

public class TestExercise {


   ExerciseAndWorkouts loader = new ExerciseAndWorkouts("src/uk/ac/aber/cs221/group10/app/data/Data.xml");
   NodeList nlworkouts = loader.getWorkout();
   // Initialises class to be tested on

   Exercise testExercise = new Exercise(nlworkouts.item(0));

   /**
    * Tests values generated in constructor are assigned properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseConstructor(){
      assertNull(testExercise.exerciseName);

      assertNull(testExercise.description);

      assertEquals(testExercise.exerciseID, 0);

      assertNull(testExercise.exerciseVideoFile);

      assertNull(testExercise.exerciseType);
   }

   /**
    * Tests get method for name works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetName(){
      assertNull(testExercise.getExerciseName());

      String testName = "Air Squats";
      testExercise.exerciseName = testName;
      assertEquals(testExercise.getExerciseName(), testName);
   }

   /**
    * Tests get method for description works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetDescription(){
      assertNull(testExercise.getDescription());

      String testDescription = "Lie down";
      testExercise.description = testDescription;
      assertEquals(testExercise.getDescription(), testDescription);
   }

   /**
    * Tests get method for exercise ID works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetExerciseID(){
      assertEquals(testExercise.getExerciseID(), 0);

      int testID = 1;
      testExercise.exerciseID = testID;
      assertEquals(testExercise.getExerciseID(), testID);
   }

   /**
    * Tests get method for video works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetVideoFile(){
      assertNull(testExercise.getExerciseVideoFile());

      File testFile = new File("/uk/ac/aber/cs221/group10/media/AirSquat.mp4");
      testExercise.exerciseVideoFile = testFile;
      assertEquals(testExercise.getExerciseVideoFile(), testFile);
   }

   /**
    * Tests get method for exercise type works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetType(){
      assertNull(testExercise.getExerciseType());
   }
}