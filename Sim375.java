public class Sim375{
  // Main fucntion that runs the program
  // Its creates an instance of the display class, and passes the arguments of the number of iterations and which type of
  // simulation to run from the terminal
  public static void main(String[] args){
   new display(Integer.parseInt(args[0]),args[1].toUpperCase());
    }
  } 
