// Imported the JFrame class to use to create the frame to display the simulation
import javax.swing.JFrame;

// Display class extends the JFrame class so the class is the JFrame so i don't have to create an instance of the class in order to use the methods
public class display extends JFrame{
  // Creating the class variables of "String a" and "int iterate" which are what were passed from the terminal
  int iterate;
  private String a;

  // Creating the constructor of the display class
  public display(int iterate, String a){
     this.iterate = iterate;
     this.a = a;
     // Calls the frame method defined below which would create the JFrame and pass the variables from the terminal to the panel which would display the iterations on the JFrame
     frame();
  }

  // Frame method creates the JFrame according to the specifications defined below i.e. the size and stopping the program when the JFrame is closed
  private void frame(){
    setSize(600,600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // Creates an instance of the life class and passes the terminal variables
    life p = new life(iterate, a);
    // Puts the life onto the JFrame
    setContentPane(p);
    // JFrame is visible
    setVisible(true);
  }
}