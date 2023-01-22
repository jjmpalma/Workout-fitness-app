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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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


   ExerciseAndWorkouts loader = new ExerciseAndWorkouts("src/uk/ac/aber/cs221/group10/app/data/TestData.xml");
   NodeList nlexercise = loader.getExercise();
   // Initialises class to be tested on

   Exercise testExercise = new Exercise(nlexercise.item(0));

   /**
    * Tests values generated in constructor are assigned properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseConstructor(){
      assertEquals(testExercise.exerciseName, "High Knees");

      assertEquals(testExercise.description, "High Knees - Alternating legs, lift your knee as high as possible then alternate legs.");

      assertEquals(testExercise.exerciseID, 5);

      assertNotNull(testExercise.exerciseVideoFile);

      assertNotNull(testExercise.exerciseType);
   }

   /**
    * Tests get method for name works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetName(){
      assertEquals(testExercise.getExerciseName(),"High Knees");
   }

   /**
    * Tests get method for description works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetDescription(){
      assertEquals(testExercise.getDescription(), "High Knees - Alternating legs, lift your knee as high as possible then alternate legs.");
   }

   /**
    * Tests get method for exercise ID works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetExerciseID(){
      assertEquals(testExercise.getExerciseID(), 5);
   }

   /**
    * Tests get method for video works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetVideoFile(){
      assertNotNull(testExercise.getExerciseVideoFile());
   }

   /**
    * Tests get method for exercise type works properly.
    * @see Exercise
    */
   @Test
   public void TestExerciseGetType(){
      assertNotNull(testExercise.getExerciseType());
   }
}