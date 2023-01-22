/*
 * @(#) WorkoutRead.java 1.1 2021/04/29
 *
 * Copyright (c) 2021 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group10.app;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;

/**
 * A class designed to read information from a history .xml document,
 * and format it into a linkedlist of WorkoutHistory.
 *
 * @author Alex Clive
 *
 * @version 1.1 Initial Development
 */

public class  WorkoutRead extends XMLio{

   /**
    * calls XMLio constructor.
    * @param FileName directory of file to open.
    */
   public WorkoutRead(String FileName){
      super(FileName);
   }

   /**
    * Reads the attribute attName of node.
    * @param node The node to read the attribute from.
    * @param attName The name of the attribute.
    * @return The value of the attribute.
    */
   private String getAttribute(org.w3c.dom.Node node, String attName ){
      return node.getAttributes().getNamedItem(attName).getNodeValue();
   }

   /**
    * Generates a Linked List containing every workout done.
    * @return A Linked List containing every workout done.
    */
   public LinkedList<WorkoutHistory> getRecords(){
      //set up return value
      LinkedList<WorkoutHistory> llwoh = new LinkedList<>();
      NodeList records = XpathQuery("/history/month/record");

      // generate all records by
      for (int i = 0; i < records.getLength() ; i++) {

         //getting each attribute
         String date = getAttribute(records.item(i), "day") + "/" +
                 getAttribute(records.item(i).getParentNode(), "month") + "/" +
                 getAttribute(records.item(i).getParentNode(), "year");
         String exDone = exercisesToString(records.item(i));
         String work = getAttribute(records.item(i), "work");
         String rest = getAttribute(records.item(i), "rest");
         String midrest = getAttribute(records.item(i), "midrest");

         //and placing into linked list with new workout history
         llwoh.add(new WorkoutHistory(date, exDone, work, rest, midrest));
      }
      return llwoh;
   }


   /**
    * Converts a record node with exercise children to a string of said exercises.
    * @param node record node.
    * @return string of exercises.
    */
   private String exercisesToString(Node node) {
      StringBuilder sb = new StringBuilder();
      NodeList exDone = node.getChildNodes();
      for (int i = 0; i < exDone.getLength(); i++) {
         sb.append(exDone.item(i).getTextContent());
         sb.append(", ");
      }

      return sb.toString();
   }
}
