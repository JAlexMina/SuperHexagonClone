import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Line2D;
import java.util.ArrayList;



public class hexaPanel extends JPanel implements KeyListener
{
   private final int x = 1280;
   private final int y = 720;
   int r = 0;
   
   
   private int CenterPointx = 600;
   private int CenterPointy = 300;
   
   
   private int triangleX_1 = (CenterPointx);
 
   private int triangleY_1 = (CenterPointy - 108);

   
   private int tS = 0;
   
   private int tAngle = 270;
   private boolean rightPressed = false;
   private boolean leftPressed = false;
   
   private int objRotation = 0;
   private int currentObstacle = 1;
   
   
   private BufferedImage hexaImage;


   private Graphics hexaBuffer;
   ArrayList<Obstacle> obstacles;

   
   Time t = new Time();
   int time = (int)(t.getAgeInMilliSeconds(1))/1000;
   
  
   public void setTS(int _ts)
   {
      tS = _ts;
   }
   
   
   
   public hexaPanel()
   {
      
      hexaImage = new BufferedImage (x, y, BufferedImage.TYPE_INT_RGB);
      hexaBuffer = hexaImage.getGraphics();
      obstacles = new ArrayList<>();
      
      addKeyListener(this);
      
      setFocusable(true);  
   }
   
   
   public void paintComponent(Graphics g)
   {    
      
      g.drawImage(hexaImage, 0, 0, getWidth(), getHeight(), null);
   }
   
   
   
   
   @Override
   public void keyTyped (KeyEvent e)
   {
   }
   
   @Override
   public void keyPressed(KeyEvent e)  
   {
      
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
      {
         rightPressed = true;
      }
      else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
      {
         leftPressed = true;
      }
   }
   
   @Override
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
      {      
         rightPressed = false;
      }
      else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
      {
         leftPressed = false;
      }
   }  
   
   
   
   public void run(boolean newObstacle, int SS, int dir, int S, int tS, int [] backgroudColor1, int [] backgroudColor2)
   {   
      
      //update keys
      if (rightPressed)
      {
            triangleX_1 = (int)(tS * (Math.cos( Math.toRadians(tAngle))) + CenterPointx + 10);  
            triangleY_1 = (int)(tS * (Math.sin( Math.toRadians(tAngle))) + CenterPointy + 10);  
            tAngle = tAngle + 3;
      }
      else if (leftPressed)
      {
            triangleX_1 = (int)(tS * (Math.cos( Math.toRadians(tAngle))) + CenterPointx + 10);  
            triangleY_1 = (int)(tS * (Math.sin( Math.toRadians(tAngle))) + CenterPointy + 10);  
            tAngle = tAngle - 3;
      }
      
//                HEXAGON SHAPE DIAGRAM
//_________________________________________________________________________________
//
//                                         yDist2
//                                           |   
//                                   yDist1  |
//                                     |     | 
//                                     v     v
//
//                             O           O -
//                              O      -  O  |
//                                     |     |
//                                     |     |
//                                     |     |
// SS_plus_OW -->      |-------------| |     |
//                     O O           O -     -   O O
// SS         -->        |-----------|
//                    
//                     
//                              |----|
// xDist1     -->               O         O 
//                             O           O
// xDist2     -->              |-----|
//
//
// 
      
      double x1 = 0.5; //( Math.cos( Math.toRadians( 60 )) );
      double y1 = 0.866025403784; //( Math.sin( Math.toRadians( 60 )) );
      
      ////////////////////////////////////////Center Hexagon//////////////////////////////////////////////////////////// 
      
      int innerHexagonWidth = 18;
      int S_plus_IHW = S + innerHexagonWidth;
      
      int ih_xDist1 = (int)(x1*S);
      int in_xDist2 = (int)(x1*S_plus_IHW);
      
      int ih_yDist1 = (int)(y1*S);
      int in_yDist2 = (int)(y1*S_plus_IHW);
   
      int innerHexagonXs [] = {(CenterPointx + S_plus_IHW), (CenterPointx + in_xDist2), (CenterPointx - in_xDist2), 
                               (CenterPointx - S_plus_IHW), (CenterPointx - in_xDist2),(CenterPointx + in_xDist2), 
                               (CenterPointx + S_plus_IHW), (CenterPointx + S), (CenterPointx + ih_xDist1), 
                               (CenterPointx - ih_xDist1),(CenterPointx - S), (CenterPointx - ih_xDist1), 
                               (CenterPointx + ih_xDist1), (CenterPointx + S)};
                               
      int innerHexagonYs [] = {(CenterPointy), (CenterPointy + in_yDist2), (CenterPointy + in_yDist2), 
                               (CenterPointy), (CenterPointy - in_yDist2), (CenterPointy - in_yDist2),
                               (CenterPointy), (CenterPointy), (CenterPointy - ih_yDist1), 
                               (CenterPointy - ih_yDist1), (CenterPointy), (CenterPointy + ih_yDist1),
                               (CenterPointy + ih_yDist1), (CenterPointy)};
                               
                               
      ///////////////////////////////////////////End Center Hexagon/////////////////////////////////////////////////////
      
      ///////////////////////////////////////////////Obstacle///////////////////////////////////////////////////////////
      
      if(newObstacle)
      {
         obstacles.add(new Obstacle(SS, S, CenterPointx, CenterPointy));
      }     
      
      Shape innerHexagon = new Polygon(innerHexagonXs, innerHexagonYs, innerHexagonXs.length);
   
      
      int circleX = triangleX_1;
      int circleY = triangleY_1;
      
      
      
      AffineTransform identity = new AffineTransform();
   
      Graphics2D g2d = (Graphics2D)hexaBuffer;
       
      g2d.setTransform(identity);
   
      clear(g2d, backgroudColor1, backgroudColor2, S, (S+18));
      rotate(g2d, dir);
          
      
      g2d.setColor(new Color(0, 0, 0));
      
      g2d.draw(new Line2D.Double(CenterPointx + (int)(S+innerHexagonWidth), CenterPointy, CenterPointx + 900, CenterPointy));
      g2d.draw(new Line2D.Double(CenterPointx - (int)(S+innerHexagonWidth), CenterPointy, CenterPointx - 900, CenterPointy));
      g2d.draw(new Line2D.Double(CenterPointx + (int)(x1*(S+innerHexagonWidth)), CenterPointy + (int)(y1*(S+innerHexagonWidth)), CenterPointx + 1000, (((((int)(y1*S)-13)*1000)/(int)(x1*S)) + CenterPointx)));
      g2d.draw(new Line2D.Double(CenterPointx - (int)(x1*(S+innerHexagonWidth)), CenterPointy + (int)(y1*(S+innerHexagonWidth)), CenterPointx - 1000, (((((int)(y1*S)-13)*1000)/(int)(x1*S)) + CenterPointx)));
      
      g2d.draw(new Line2D.Double(CenterPointx + (int)(x1*(S+innerHexagonWidth)), CenterPointy - (int)(y1*(S+innerHexagonWidth)), CenterPointx + 1000, ((-(((int)(y1*S)+13)*1000)/(int)(x1*S)) + CenterPointx)));
      g2d.draw(new Line2D.Double(CenterPointx - (int)(x1*(S+innerHexagonWidth)), CenterPointy - (int)(y1*(S+innerHexagonWidth)), CenterPointx - 1000, ((-(((int)(y1*S)+13)*1000)/(int)(x1*S)) + CenterPointx)));
       
      g2d.fill(innerHexagon);
      

      for(int k = 0; k < obstacles.size(); k++)
      {
         Obstacle ob = obstacles.get(k);
         if(!ob.update(SS, S, CenterPointx, CenterPointy))
         {
            //returns false when the obstacle has ended
            obstacles.remove(k);
            k--;
         }
         else
         {         
            for (int i = 0; i < ob.obstacleListXs.size(); i++)  
            {
               g2d.fill(new Polygon(ob.obstacleListXs.get(i), ob.obstacleListYs.get(i), ob.obstacleListXs.get(i).length));
            }
         }
      }

      g2d.fillOval((circleX - 20), (circleY - 20), (20), (20)); 

      
      repaint();     
   }
   
   
   public void rotate(Graphics2D g2d, int dir)
   {
      g2d.rotate(Math.toRadians(r), (CenterPointx), (CenterPointy)); // rotates about transformed origin
      r = r + dir;
   }
   
   public void clear(Graphics2D g2d, int [] backgroudColor, int [] backgroudColor1, int S, int S2)
   {
      g2d.setColor(new Color(backgroudColor[0], backgroudColor [1], backgroudColor [2]));
      
      int topCx [] = {};
      int topCy [] = {};
      
      g2d.fillRect(0,0,x,y);
      
      repaint();
   }
   
   
}