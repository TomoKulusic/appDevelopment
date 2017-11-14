import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MainTester {

      
   public static void main(String [] args){
   
      EdgeTableTest ett;   
      String command = null;
      String DEFAULT_VALUE = "1|student";
      String help = " You have two options, -h prints out the help messages";
      String help2 = " -n Is for what follows the test object";
      String help3 = " -f Is for what follows the name of a test object file";
       
      if(args.length == 0){
         
         Result result = JUnitCore.runClasses(EdgeTableTest.class);
		
         for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
         }
		
         System.out.printf("Test ran: %s, Failed: %s%n",result.getRunCount(), result.getFailureCount());
          
      }
      else if(args[0].equals("-h")) {
         System.out.println(help);
         System.out.println(help2);
         System.out.println(help3);
      }
      else if(args[0].equals("-n")) {
         command = args[1];
         //ett = new EdgeTableTest(command);
      }
      else if(args[0].equals("-f")) {
         System.out.println("This is the test object " + args[1]);
      }
      
   
   
         
   
   
   }//main Method






}//end of class