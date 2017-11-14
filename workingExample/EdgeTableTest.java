import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
nov 10 2017
*/

public class EdgeTableTest {

   EdgeTable testObj;
         
   @Before
   public void setUp() throws Exception {
      testObj = new EdgeTable("1|student");
      //runner();
   }
   
   public void runner() {
        
      testGetNumFigure();
      testGetName();
     
   }
   
   @Test
   public void testGetNumFigure() {
      System.out.println("getNumFigure is running!");
      assertEquals("testGetNum was intialized to 1 so it should be 1",1,testObj.getNumFigure());      
   }

   @Test
   public void testGetName() {
     System.out.println("testGetName is running!");
     assertEquals("testGetName was intialized to student so it should be student","student",testObj.getName());
   }
}