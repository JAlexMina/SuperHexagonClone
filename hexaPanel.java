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
      System.out.println("KeyTyped");
   
   }
   
   @Override
   public void keyPressed(KeyEvent e)  
   {
      
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
      {
         
         rightPressed = true;
         
         System.out.println("R KeyPressed");
      }
      else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
      {
         
         leftPressed = true;
         
         
         System.out.println("L KeyPressed");
      }
   }
   
   @Override
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
      {
         
         rightPressed = false;
         
         System.out.println("R KeyPressed");
      }
      else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
      {
         

         leftPressed = false;
         
         System.out.println("L KeyPressed");
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
//                             |----|
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
      
      ArrayList<int[]> obstacleListXs = new ArrayList<>();
      ArrayList<int[]> obstacleListYs = new ArrayList<>();    
      
      int obstacleWidth = 30;      
      int SS_plus_OW = SS + obstacleWidth;
      
      int obst_xDist1 = (int)(x1*SS);
      int obst_xDist2 = (int)(x1*SS_plus_OW);
      
      int obst_yDist1 = (int)(y1*SS);
      int obst_yDist2 = (int)(y1*SS_plus_OW);
      
      //obstacle type
      Random random = new Random();
      if(newObstacle)
      {
         currentObstacle = random.nextInt(3) + 1; // Generates a number from 1 to 3 
      }

      if(currentObstacle == 1)
      {
         ///////// (4 side) /////////
         int obstacleXs [] = {(CenterPointx + SS),          (CenterPointx + SS_plus_OW),
                               (CenterPointx + obst_xDist2), (CenterPointx - obst_xDist2), 
                               (CenterPointx - SS_plus_OW),  (CenterPointx - obst_xDist2), 
                               (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1), 
                               (CenterPointx - obst_xDist1), (CenterPointx - SS), 
                               (CenterPointx - obst_xDist1), (CenterPointx + obst_xDist1)};
                              
         int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                               (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist2), 
                               (CenterPointy),                (CenterPointy - obst_yDist2 ), 
                               (CenterPointy - obst_yDist2),  (CenterPointy - obst_yDist1), 
                               (CenterPointy - obst_yDist1),  (CenterPointy), 
                               (CenterPointy + obst_yDist1),  (CenterPointy + obst_yDist1)};
                               
         obstacleListXs.add(obstacleXs);
         obstacleListYs.add(obstacleYs);
      }
      else if(currentObstacle == 2)
      {                 
         ///////// (1 side) /////////
         int obstacleXs [] = {(CenterPointx + SS),          (CenterPointx + SS_plus_OW),
                              (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                              
         int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                              (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                               
         obstacleListXs.add(obstacleXs);
         obstacleListYs.add(obstacleYs);
      }
      else if(currentObstacle == 3)
      {                 
         ///////// (2 side opposite) /////////
         int obstacleXs [] = {(CenterPointx + SS),          (CenterPointx + SS_plus_OW),
                              (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                              
         int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                              (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                               
         obstacleListXs.add(obstacleXs);
         obstacleListYs.add(obstacleYs);
         
         int obstacleXs2 [] = {(CenterPointx - SS),          (CenterPointx - SS_plus_OW),
                               (CenterPointx - obst_xDist2), (CenterPointx - obst_xDist1)};
                              
         int obstacleYs2 [] = {(CenterPointy),                (CenterPointy), 
                               (CenterPointy - obst_yDist2 ), (CenterPointy - obst_yDist1)};
                               
         obstacleListXs.add(obstacleXs2);
         obstacleListYs.add(obstacleYs2);
      }
                           
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      //rotate obstacles randomly
      
      
      
         // allowed rotation angles in degrees
         int[] angles = {60, 120, 180, 240, 300};
         if(newObstacle)
         {
            objRotation = random.nextInt(angles.length);
         }
         
         for (int i = 0; i < obstacleListXs.size(); i++)  
         {
            for (int j = 0; j < obstacleListXs.get(i).length; j++) 
            {
               // pick a random angle from the list
               int angleDeg = angles[objRotation];
               double angleRad = Math.toRadians(angleDeg);
               
               // translate point so that (cx, cy) becomes origin
               double dx = obstacleListXs.get(i)[j] - CenterPointx;
               double dy = obstacleListYs.get(i)[j] - CenterPointy;
               
               // apply rotation:
               // x' = dx*cosθ - dy*sinθ
               // y' = dx*sinθ + dy*cosθ
               double rotatedX = dx * Math.cos(angleRad) - dy * Math.sin(angleRad);
               double rotatedY = dx * Math.sin(angleRad) + dy * Math.cos(angleRad);
               
               //System.out.println("(" + (int) Math.round(CenterPointx + rotatedX) + ", " + (int) Math.round(CenterPointy + rotatedY) + ") is (" + obstacleListXs.get(i)[j] + ", " + 
               //                     obstacleListYs.get(i)[j] + ") rotated " + angleDeg + " around (" + CenterPointx + ", " + CenterPointy + ")");
         
               // translate back and store
               obstacleListXs.get(i)[j] = (int) Math.round(CenterPointx + rotatedX);
               obstacleListYs.get(i)[j] = (int) Math.round(CenterPointy + rotatedY);
               
               
            }
         }
      
      
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////                     


      
      ArrayList<Shape> obstacleShapes = new ArrayList<>();
      for (int i = 0; i < obstacleListXs.size(); i++)  
      {
         obstacleShapes.add(new Polygon(obstacleListXs.get(i), obstacleListYs.get(i), obstacleListXs.get(i).length));
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
      
      for (Shape obstacle : obstacleShapes) 
      {
         g2d.fill(obstacle);
      }

      g2d.fillOval((circleX - 20), (circleY - 20), (20), (20)); 
      
      //System.out.println("circleX: " + circleX);
      //System.out.println("circleY: " + circleY);
      
   
      
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
      
      /*int y1 = (int)(Math.sin( Math.toRadians( 60 ) ) * S);
      int x1 = (int)(Math.cos( Math.toRadians( 60 ) ) * S);
      int y1 = (int)(Math.sin( Math.toRadians( 60 ) ) * S2);
      int x1 = (int)(Math.cos( Math.toRadians( 60 ) ) * S2);
      int x3 = S;
      int x4 = S2;*/
      
      int topCx [] = {};
      int topCy [] = {};
      
      
      
      //g2d.fillPolygon(topCx, topCy, topCx.length);
      
      g2d.fillRect(0,0,x,y);
      
      repaint();
   }
   
   
}