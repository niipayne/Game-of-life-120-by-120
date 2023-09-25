import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class tumbler extends JPanel implements ActionListener{
  int xSize;
  int ySize;
  
  boolean b;

  boolean start = true;
  int magnification = 5;
  int [][] cells;
  int [][] newCells;
  int yLength;
  int xLength;
  String a;

  tumbler(int width, int height){
    this.xSize = width;
    this.ySize = height;

    cells = new int[xSize/magnification][ySize/magnification];
    newCells = new int[xSize/magnification][ySize/magnification];
    yLength = ySize/magnification;
    xLength = xSize/magnification;
    new Timer(80,this).start();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    setBackground(Color.BLACK);
      cell();
      draw(g);
  }

  private void cell(){
    if(start){
      int x = 10;
      int y = 10;
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

  private void draw(Graphics g){
    duplicate();
    super.paintComponent(g);
    g.setColor(Color.GREEN);
    for(int x = 0; x < xLength; x++){
        for(int y =0; y< (yLength); y++){
            if(cells[x][y] == 1){
              g.fillRect( x*magnification, y*magnification, magnification, magnification);
            }
      }
    }
  }

  public void duplicate(){
     for(int x = 0; x < xLength; x++){
        for(int y =0; y< (yLength); y++){
          cells[x][y] = newCells[x][y];
        }
      }
  }

  public int neighbourhood(int x,int y){
    int alive = 0;
    
    alive += cells[(x + xLength - 1) % xLength][(y - 1 + yLength) % yLength];

    alive += cells[(x + xLength) % xLength][(y - 1 + yLength) % yLength];
    
    alive += cells[(x + 1 + xLength) % xLength][(y - 1 + yLength) % yLength];

    alive += cells[(x - 1 + xLength ) % xLength][(y + yLength) % yLength];

    alive += cells[(x + 1 + xLength ) % xLength][(y + yLength) % yLength];

    alive += cells[(x - 1 + xLength ) % xLength][(y + 1 + yLength) % yLength];

    alive += cells[(x + xLength ) % xLength][(y + 1 + yLength) % yLength];

    alive += cells[(x + 1 + xLength ) % xLength][(y + 1 + yLength) % yLength];
        
    return alive;
    }
    
  

  public void actionPerformed(ActionEvent e){

    int living = 0;

     for(int x = 0; x < xLength; x++){
        for(int y =0; y< (yLength); y++){

          living = neighbourhood(x, y);
          if(living == 3){
            newCells[x][y] = 1;
        }
        else if(living == 2 && cells[x][y] == 1 ){
            newCells[x][y] = 1;
        }
        else if(living == 3 && cells[x][y] == 1 ){
            newCells[x][y] = 1;
        }
        else {
          newCells[x][y] = 0;
        }
    }
    repaint();
    }
  }
}

