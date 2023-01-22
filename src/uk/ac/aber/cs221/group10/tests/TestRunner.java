/*
       * @(#) TestRunner.java 1.0 2021/04/30
       *
       * Copyright (c) 2021 Aberystwyth University.
       * All rights reserved.
       *
       */

package uk.ac.aber.cs221.group10.tests;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner.java - A test class, runs the existing test classes (TestExercise.java & TestWorkoutRoutinesConfig.java)
 * And prints their results into a text file dated in the format yyyy-mm-dd_v_x_module_test_report.txt inside the Module Tests folder.
 * Class is run after modules are built and ready to be tested.
 *
 * @author  Bartek S [bas22]
 * @author  Ahmed Z [ahz1]
 * @version 1.0 Initial Development.
 * @see  TestExercise
 * @see  uk.ac.aber.cs221.group10.app.Exercise
 * @see  TestWorkoutRoutinesConfig
 * @see  uk.ac.aber.cs221.group10.app.WorkoutRoutinesConfig
 */

public class TestRunner {

   /**
    * Main class, creates the dated file module_test_report.txt and runs the test classes and outputs the results to both
    * the console & .txt file.
    *
    * @param args default parameters for a main method.
    * @exception java.io.IOException   if write permissions do not exist for the text file.
    */
   public static void main(String[] args) throws java.io.IOException {

      // fileName = "yyyy-MM-dd_v_x_module_test_report_.txt"
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDateTime now = LocalDateTime.now();
      int version = 1;

      String fileName = "out/production/Tests/Module Tests/" + dtf.format(now) + "_v_" + version + "_module_test_report.txt";
      File resultsFile = new File(fileName);

      while (!resultsFile.createNewFile()) {
         version++;
         fileName = "out/production/Tests/Module Tests/" + dtf.format(now) + "_v_" + version + "_module_test_report.txt";
         resultsFile = new File(fileName);
      }

      if (resultsFile.canWrite()) {
         FileWriter writer = new FileWriter(resultsFile);
         // Assigns the Test classes ran to the result.
         Result result = JUnitCore.runClasses(TestWorkoutRoutinesConfig.class,  TestExercise.class);
         // For every test failed within the results classes, will print out the failure.

         for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
         }

         System.out.println(result.wasSuccessful()); // Prints false if unsuccessful & true otherwise.
         String results = "Result: Failures: " +
                 result.getFailureCount() + ". Ignored: " +
                 result.getIgnoreCount() + ". Tests run: " +
                 result.getRunCount() + ". Time: " +
                 result.getRunTime() + "ms.";
         System.out.println(results);
         writer.write(results);
         writer.flush();
         writer.close();
      } else {
         System.out.println("Could not write to results file");
      }
   }
}