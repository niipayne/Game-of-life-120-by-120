// Importing all the classes that are going to be used
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// life class extends the JPanel so the class is the panel itself so i don't have to create an instance of the class
public class life extends JPanel implements ActionListener{
  // Creating all the class variables
  boolean start = true;
  int magnification = 5;
  int [][] cells;
  int [][] newCells;
  String a;
  Timer timer;
  int iterate;
  int count = 0;
  int xSize = 600;
  int ySize = 600;

  // Constructor  for the life class which creates the tables for the cells and defines the timer and runs the method run().
  life(int iterate, String a){
    this.a = a;
    this.iterate = iterate;

    cells = new int[120][120];
    newCells = new int[120][120];
    
    // Listener of the timer is this class because it implements the actionlistener class so it would call the actionperformed method when it goes off
    timer = new Timer(100, this);
    run();
  }

  // Starts the timer which calls the actionperformed method 
  public void run(){
    timer.start();
  }
  
  // This is the method responsible for rendering the graphics onto the panel
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    setBackground(Color.BLACK);
    // This section is to check which type of simulation is selected i.e. R for random and T for Tumbler and L for the loafer glider selects the cells accordingly and calls the draw function
    if(a.equals("R")){
      cell();
      draw(g);
    }
    else if (a.equals("T")){
      tum();
      draw(g);
    }
    else if (a.equals("L")){
      glider();
      draw(g);
    } 
  }

  // This is the method that is called everytime the timer goes off
  public void actionPerformed(ActionEvent e){
    // Counter to keep track of the number of iterations of the simulation
    count++;
    if(count > iterate){
      // If the counter is higher then the number of iterations entered then the timer stops so the simulation stops too else run the simiulation
      timer.stop();
    }
    else {
      // Living variable keeps track of the number of living neighbours according to the Moore neighbourhood scheme
    int living = 0;
      // For loop to go through each cell
     for(int x = 0; x < 120; x++){
        for(int y =0; y< (120); y++){
          // Count the number of living cells in the Moore neighbourhood
          living = neighbourhood(x, y);
          // If the cell has 3 in its neighbourhood they give birth to the cell 
          if(living == 3){
            newCells[x][y] = 1;
        }
        // if the cell has 2 living and itself is alive then it lives to the next iteration
        else if(living == 2 && cells[x][y] == 1 ){
            newCells[x][y] = 1;
        }
        // Otherwise they remain dead
        else{
          newCells[x][y] = 0;
        }
    }
    // This calls the paintComponent method which draws the cells on the JPanel
    repaint();
    }
  }
  }  

  // Calls the duplicate method which copies the the new array into the old one and displays the old one
  private void draw(Graphics g){
    duplicate();
    super.paintComponent(g);
    // Set the color of the cells 
    g.setColor(Color.GREEN);
    for(int x = 0; x < 120; x++){
        for(int y =0; y< (120); y++){
            if(cells[x][y] == 1){
              // Fills the cells 
              g.fillRect( x*magnification, y*magnification, magnification, magnification);
            }
      }
    }
  }

  // Copies the newCells into the old cells 
  public void duplicate(){
     for(int x = 0; x < 120; x++){
        for(int y =0; y< (120); y++){
          cells[x][y] = newCells[x][y];
        }
      }
  }

  // When cell is called which is invoked when the random simulation is selected it goes through each cell using a for loop and randomly assign dead or alive
  private void cell(){
    if(start){
      for(int x = 0; x < 120; x++){
        for(int y =0; y < 120; y++){

        if((Math.random()) < 0.5){
          newCells[x][y] = 1;
        }
      }
    }
    start = false;
  }
}

// Manually sets the cells that will be alive in order to create the tumbler oscillator
  private void tum(){
    if(start){
      int x = 4;
      int y = 4;
      newCells[x][y] = 1;
      newCells[x-1][y+1] = 1;
      newCells[x-1][y+2] = 1;
      newCells[x+1][y+1] = 1;
      newCells[x+2][y+2] = 1;
      newCells[x+1][y+3] = 1;
      newCells[x+1][y+4] = 1;
      newCells[x+5][y+4] = 1;
      newCells[x+5][y+3] = 1;
      newCells[x+4][y+2] = 1;
      newCells[x+5][y+1] = 1;
      newCells[x+6][y] = 1;
      newCells[x+7][y+1] = 1;
      newCells[x+7][y+2] = 1;
      newCells[x+2][y+4] = 1;  
      newCells[x+4][y+4] = 1;
    }
    start = false;
  }

  // Manually sets the cells that will be alive in order to create the loafer glider
  private void glider(){
    if(start){
      int x = 4;
      int y = 4;
      newCells[x][y] = 1;
      newCells[x-1][y-1] = 1;
      newCells[x-2][y-1] = 1;
      newCells[x-3][y] = 1;
      newCells[x-2][y+1] = 1;
      newCells[x-1][y+2] = 1;
      newCells[x][y+1] = 1;
      newCells[x+2][y-1] = 1;
      newCells[x+3][y] = 1;
      newCells[x+4][y] = 1;
      newCells[x+4][y-1] = 1;
      newCells[x+5][y-1] = 1;
      newCells[x+5][y+3] = 1;
      newCells[x+5][y+4] = 1;
      newCells[x+4][y+4] = 1;
      newCells[x+3][y+4] = 1;
      newCells[x+2][y+5] = 1;
      newCells[x+3][y+6] = 1;
      newCells[x+4][y+7] = 1;
      newCells[x+5][y+7] = 1;
    }
    start = false;
  }
  

  // This method creates the wrap around of the grid forming the "torus". Additionally, it counts the number of living neighbouring cells around a current cell according to Moore's neighbourhood
  private int neighbourhood(int x,int y){
    int alive = 0;
            
          alive += cells[((x) + 120 - 1) % 120][(y - 1 + 120) % 120];

          alive += cells[(x + 120) % 120][(y - 1 + 120) % 120];
          
          alive += cells[((x + 1) + 120) % 120][(y - 1 + 120) % 120];

          alive += cells[((x - 1) + 120 ) % 120][(y + 120) % 120];

          alive += cells[((x + 1) + 120) % 120][(y + 120) % 120];

          alive += cells[((x - 1) + 120 ) % 120][(y + 1 + 120) % 120];

          alive += cells[((x) +120 ) % 120][(y + 1 + 120) % 120];

          alive += cells[((x + 1) + 120 ) % 120][(y + 1 + 120) % 120];
        
      return alive;
    }
  }
    
  

  

