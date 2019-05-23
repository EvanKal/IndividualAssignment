/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Classes.IndividualAssignment;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Los_e
 */
public class PrintUtils {
    
    public static void printList(ArrayList arraylist) {

    if(arraylist.size()>0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
        for (int i = 0; i < arraylist.size(); i++) {
            if(arraylist.get(i) instanceof IndividualAssignment) {
            System.out.println(">>> " + (i+1) +". " + arraylist.get(i).toString());
//            System.out.println(arraylist.get(i).toString());
            } else {
            System.out.println(">>> " + (i+1) +". " + arraylist.get(i).toString());
//            System.out.println(arraylist.get(i).toString());
            }

        }
    }else if(arraylist.isEmpty()) {
        System.out.println("Currently no items in list");
    }
    
}
    
    
}
