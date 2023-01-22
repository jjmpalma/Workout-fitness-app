/*
 * @(#) workoutHistory.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 *  All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import javafx.beans.property.SimpleStringProperty;

/**
 * A structure used to represent a single past workout.
 * includes both the routine and exercises done
 *
 * @author Juan Manuel Palma
 *
 * @version 1.1 Initial Development
 */

public class WorkoutHistory {

   //Variables modelling previous workouts data
   private SimpleStringProperty dateColumn;
   private SimpleStringProperty exercisesColumn;
   private SimpleStringProperty workTimeColumn;
   private SimpleStringProperty restTimeColumn;
   private SimpleStringProperty midRestTimeColumn;

   /**
    * Constructor
    *
    * @param dateColumn date
    * @param exercisesColumn exercises done (as a string)
    * @param workTimeColumn work time
    * @param restTimeColumn rest time
    * @param midRestTimeColumn middle rest time
    */
   public WorkoutHistory(String dateColumn, String exercisesColumn, String workTimeColumn, String restTimeColumn, String midRestTimeColumn) {
      this.dateColumn = new SimpleStringProperty(dateColumn);
      this.exercisesColumn = new SimpleStringProperty(exercisesColumn);
      this.workTimeColumn = new SimpleStringProperty(workTimeColumn);
      this.restTimeColumn = new SimpleStringProperty(restTimeColumn);
      this.midRestTimeColumn = new SimpleStringProperty(midRestTimeColumn);
   }

   //Read Write properties - Required to populate Table View in home.fxml and workoutHistory.fxml
   public String getDateColumn() {
      return dateColumn.get();
   }

   public SimpleStringProperty dateColumnProperty() {
      return dateColumn;
   }

   public void setDateColumn(String dateColumn) {
      this.dateColumn.set(dateColumn);
   }

   public String getExercisesColumn() {
      return exercisesColumn.get();
   }

   public SimpleStringProperty exercisesColumnProperty() {
      return exercisesColumn;
   }

   public void setExercisesColumn(String exercisesColumn) {
      this.exercisesColumn.set(exercisesColumn);
   }

   public String getWorkTimeColumn() {
      return workTimeColumn.get();
   }

   public SimpleStringProperty workTimeColumnProperty() {
      return workTimeColumn;
   }

   public void setWorkTimeColumn(String workTimeColumn) {
      this.workTimeColumn.set(workTimeColumn);
   }

   public String getRestTimeColumn() {
      return restTimeColumn.get();
   }

   public SimpleStringProperty restTimeColumnProperty() {
      return restTimeColumn;
   }

   public void setRestTimeColumn(String restTimeColumn) {
      this.restTimeColumn.set(restTimeColumn);
   }

   public String getMidRestTimeColumn() {
      return midRestTimeColumn.get();
   }

   public SimpleStringProperty midRestTimeColumnProperty() {
      return midRestTimeColumn;
   }

   public void setMidRestTimeColumn(String midRestTimeColumn) {
      this.midRestTimeColumn.set(midRestTimeColumn);
   }
}
