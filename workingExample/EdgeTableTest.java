import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
/**
nov 10 2017
*/

public class EdgeTableTest {

   EdgeTable testObj;
   public int testsrun = 0;
   public int testsfail = 0;
   
   public EdgeTableTest(String value) {
      testObj = new EdgeTable(value);
      runner();
      displayResults();
   
   }
         
   /*@Before
   public void setUp() throws Exception {
      testObj = new EdgeTable("1|student");
      runner();
   }*/
   
   public void runner() {
        
      testGetNumFigure();
      testGetName();
     
   }
   
   @Test
   public void testGetNumFigure() {
      testsrun++;
      System.out.println("getNumFigure is running!");
      try {
         assertEquals(1,testObj.getNumFigure());
      } catch (AssertionError e) {
         System.out.println("testGetNum was intialized to " + testObj.getNumFigure() + " ,it should be 1");
         testsfail++;
      }
   
   }

   @Test
   public void testGetName() {
      testsrun++;
      System.out.println("testGetName is running!");
      try {
         assertEquals("student",testObj.getName());
      } catch (AssertionError e) {
         System.out.println("testGetName was intialized to " + testObj.getName() + " so it should be student");
         testsfail++;
      }
   
   }
   
   
   public void displayResults() {
      System.out.println("Tests run: " + testsrun + " Tests failed: " + testsfail );
   }
   
}