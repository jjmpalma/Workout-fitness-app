/*
 * @(#) WorkoutStorage.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;

/**
 * A class designed to manipulate and save to the history file
 *
 * @author Alex Clive
 *
 * @version 1.1 Initial Development
 */

public class WorkoutStorage extends XMLio{

   private  int quantity;
   private final String fileName;
   Element record;

   /**
    * Constructor, calls XMLio constructor.
    * @param saveFileName The file to be opened.
    */
   public WorkoutStorage(String saveFileName){
      super(saveFileName);
      fileName = saveFileName;
   }

   /**
    * generates a new record in the .xml file.
    * (includes saving)
    * @param routine The routine that was chosen.
    * @param exID The id's of exercises that were chosen.
    */
   public void newRecord(WorkoutRoutinesConfig routine, int[] exID) {

      //make new record
      record = super.doc.createElement("record");

      //set time values
      record.setAttribute("day", String.valueOf(LocalDate.now().getDayOfMonth()));
      record.setAttribute("intensity", String.valueOf(routine.getIntensity()));
      quantity = 0;
      record.setAttribute("quantity", "0");
      record.setAttribute("work", String.valueOf(routine.getWorkTime()));
      record.setAttribute("rest", String.valueOf(routine.getRestTime()));
      record.setAttribute("midrest", String.valueOf(routine.getMidRestTime()));

      //for each exercise id
      for (int id : exID) {
         //find the exercise name (sorry the big O isn't that good here :( )
         String exName = null;
         for (int j = 0; j < 30; j++) {
            if (Main.getExercises()[j].getExerciseID() == id) {
               exName = Main.getExercises()[j].getExerciseName();
            }
         }
         Element exEle = super.doc.createElement("exercise");
         exEle.setTextContent(exName);

         //and save (every) exercise to the List
         record.appendChild(exEle);
      }

      //adding to dom
      Node ref = XpathQuery("/history/month").item(0);

      // check if newest month / year is right
      if (Integer.parseInt(ref.getAttributes().getNamedItem("month").getNodeValue()) != LocalDate.now().getMonth().getValue()
              || Integer.parseInt(ref.getAttributes().getNamedItem("year").getNodeValue()) != LocalDate.now().getYear()){
         //when it's not right, add the right month node
         Element month = super.doc.createElement("month");
         month.setAttribute("month", String.valueOf(LocalDate.now().getMonth().getValue()));
         month.setAttribute("year", String.valueOf(LocalDate.now().getYear()));
         ref.getParentNode().insertBefore(month, ref);
      }

      //add exercise to record
      if (ref.hasChildNodes()) {
         ref.insertBefore(record, ref.getFirstChild());
      } else {
         ref.appendChild(record);
      }

      save();
   }

   /**
    * sets the quantity of workouts completed.
    * (and saves the value back to the .xml file)
    * @param exerciseQuantity # of workouts done
    */
   public void setQuantity(int exerciseQuantity){
      quantity=exerciseQuantity;
      record.setAttribute("quantity", String.valueOf(quantity));
      save();
   }

   /**
    * includes actual logic for saving, to be called whenever changes are made to the Document.
    */
   private void save(){
      //save record
      //StreamResult console = new StreamResult(System.out);
      StreamResult file = new StreamResult(fileName);

      DOMSource source = new DOMSource(doc);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transf;
      try {
         transf = transformerFactory.newTransformer();
         //transf.transform(source, console);
         transf.transform(source, file);
      } catch (TransformerException e) {
         e.printStackTrace();
      }
   }


}