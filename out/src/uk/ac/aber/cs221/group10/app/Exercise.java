/*
 * @(#) Exercise.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.Node;

import java.io.File;

/**
 * This class is used to represent an exercise and associated values.
 *
 * @author Alex Clive
 *
 * @version 1.1 Initial Development
 */

// We originally planned to implement this  enumeration but realised it was not needed.
enum ExerciseType {
   WARMUP,
   STANDARD,
   COOLDOWN
}

public class Exercise {



   public String exerciseName;

   public String description;

   public int exerciseID;

   public File exerciseVideoFile;

   public ExerciseType exerciseType;

   /**
    * Constructor using an exercise node to populate instance variables
    * @param node exercise node used to populate variables
    */
   public Exercise(Node node){
      exerciseName = node.getAttributes().getNamedItem("name").getNodeValue();
      description = node.getAttributes().getNamedItem("description").getNodeValue();
      exerciseID = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
      exerciseVideoFile = new File(node.getAttributes().getNamedItem("filePath").getNodeValue());
      exerciseType = ExerciseType.valueOf(node.getAttributes().getNamedItem("intensity").getNodeValue());
   }

   /**
    *
    * @return the exerciseName
    */
   public String getExerciseName() {
      return exerciseName;
   }

   /**
    *
    * @return the exercise description
    */
   public String getDescription() {
      return description;
   }

   /**
    *
    * @return the exercise ID
    */
   public int getExerciseID() {
      return exerciseID;
   }

   /**
    *
    * @return the file path for the video file
    */
   public File getExerciseVideoFile() {
      return exerciseVideoFile;
   }

   /**
    *
    * @return returns the exercise type enum
    */
   public ExerciseType getExerciseType() {
      return exerciseType;
   }
}