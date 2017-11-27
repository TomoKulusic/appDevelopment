import javax.swing.*;
import java.awt.*;
import java.awt.event.*;   // for ActionListener
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class HelpSystem extends JFrame implements ActionListener {

   // Attributes
   private JFrame frame;
   private JButton    jbHelp1, jbHelp2, jbHelp3, jbHelp4;
   private JButton    jbExit;
    BufferedImage myPicture ;
    ImageIcon icon2, icon3, icon4;
   
   public static void main(){
      // new HelpSystem();
   } // end main
   
   // Create the GUI
   public HelpSystem(){
       
   
        JPanel jpMain = new JPanel();
         jpMain.setBackground(Color.black);
         
         setTitle("Help System");

        try{
           myPicture = ImageIO.read(new File("person.jpg"));
        }
        catch(Exception e){
         System.out.println("Poop");
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JLabel helpLabel = new JLabel(" Welcome to the Help System");
        helpLabel.setBackground(Color.white);
        JPanel jpButtons = new JPanel( new GridLayout(0,1));
        jpButtons.setForeground(Color.white);
        jpButtons.setBackground(Color.black);
        //incase for joptionpane
        ImageIcon icon = new ImageIcon(HelpSystem.class.getResource("person.jpg"));
         icon2 = new ImageIcon(HelpSystem.class.getResource("command.PNG"));
          icon3 = new ImageIcon(HelpSystem.class.getResource("find.PNG"));
           icon4 = new ImageIcon(HelpSystem.class.getResource("sql.PNG"));

        jpButtons.setFont(new Font("Serif", Font.PLAIN, 28));
         jpButtons.setOpaque(true);
         jbHelp1 = new JButton("I need help with typing a command");
          jbHelp2 = new JButton("I can not click on any of the buttons near 'varchar', 'Boolean','ault', 'integer' ");
          jbHelp3 = new JButton("I can not see any information about the tables in the program ");
           jbHelp4 = new JButton("What does CreateDDL do? ");
         jbExit = new JButton( "Exit" );
         
         jpButtons.add( jbHelp1);
         jpButtons.add( jbHelp2);
         jpButtons.add( jbHelp3);
          jpButtons.add( jbHelp4);
        // jpButtons.add( jbExit );
         
         jbHelp1.addActionListener( this );
          jbHelp2.addActionListener( this );
          jbHelp3.addActionListener( this );
           jbHelp4.addActionListener( this );



         
         jbExit.addActionListener( this );
         
         setSize(600,600);
         jpMain.add(picLabel, BorderLayout.CENTER);
        //  jpMain.add(helpLabel, BorderLayout.SOUTH);

         
         add ( jpMain, BorderLayout.CENTER );
         add ( jpButtons, BorderLayout.SOUTH );
     
    // pack();
     setLocationRelativeTo( null );
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setVisible( true ); 
      
   } // end constructor HelpSystem
   
   public void actionPerformed(ActionEvent ae){
   
      Object choice = ae.getSource();
      
      if( choice == jbHelp1 ){
      
            JOptionPane.showMessageDialog(frame,
    "If you need help typing a command, click in the yellow area and type",
    "Help1",
    JOptionPane.QUESTION_MESSAGE, icon2);
      
              }
              
              if( choice == jbHelp2 ){
      
            JOptionPane.showMessageDialog(frame,
             "This is because you have not selected your Courses.edg file.\n You must click on file -> Open file and then select the edg file you would like to open",
    "Help2",
    JOptionPane.QUESTION_MESSAGE, icon3);
      
              }
               if( choice == jbHelp3 ){
      
            JOptionPane.showMessageDialog(frame,
              "This is because you have not selected your Courses.edg file.\n You must click on file -> Open file and then select the edg file you would like to open",
    "Help3",
    JOptionPane.QUESTION_MESSAGE, icon3);
      
              }

          if( choice == jbHelp4 ){
      
            JOptionPane.showMessageDialog(frame,
              "Create DDL will put you through a set of instructions on where to save  the msql file \n It is recommended to save the file in the same folder as the edge file was taken from","Help4",
                  JOptionPane.QUESTION_MESSAGE, icon4);
      
              }

        
   }


} // end class Day02