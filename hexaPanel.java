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






public class hexaPanel extends JPanel implements KeyListener
{
   private final int x = 1280;
   private final int y = 720;
   int r = 0;
   
   
   private int Cx = 600;
   private int Cy = 300;
   
   
   private int triangleX_1 = (Cx);
 
   private int triangleY_1 = (Cy - 108);

   
   private int tS = 0;
   
   private int tAngle = 270;
   

   
   
  
   
   
   
   
   private BufferedImage hexaImage;


   private Graphics hexaBuffer;

   
   Time t = new Time();
   int time = (int)(t.getAgeInMiliSeconds(1))/1000;
   
  
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
      //System.out.println("KeyTyped");
   
   }
   
   @Override
   public void keyPressed(KeyEvent e)  
   {
      
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
      {
         
         for(int i = 0; i < 10; i++)
         {
            triangleX_1 = (int)(tS * (Math.cos( Math.toRadians(tAngle))) + Cx + 10);  
            triangleY_1 = (int)(tS * (Math.sin( Math.toRadians(tAngle))) + Cy + 10);  
            tAngle = tAngle + 1;
            repaint();
         }
         
         //System.out.println("R KeyPressed");
      }
      else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
      {
         
         for(int i = 0; i < 10; i++)
         {
            triangleX_1 = (int)(tS * (Math.cos( Math.toRadians(tAngle))) + Cx + 10);  
            triangleY_1 = (int)(tS * (Math.sin( Math.toRadians(tAngle))) + Cy + 10);  
            tAngle = tAngle - 1;
            repaint();
         }
         
         
         //System.out.println("L KeyPressed");
      }
      repaint();
   }
   
   @Override
   public void keyReleased(KeyEvent e)
   {
   
   }  
   
   
   
   public void run(int SS, int SS2, int SSS, int SSS2, int dir, int S, int S2, int tS, int [] backgroudColor1, int [] backgroudColor2)
   {   
      
      int yy1 = (int)(Math.sin( Math.toRadians( 60 ) ) * SS);
      int xx1 = (int)(Math.cos( Math.toRadians( 60 ) ) * SS);
      int yy2 = (int)(Math.sin( Math.toRadians( 60 ) ) * SS2);
      int xx2 = (int)(Math.cos( Math.toRadians( 60 ) ) * SS2);
      int xx3 = SS;
      int xx4 = SS2;
   
      int polygonXXs [] = {(Cx + xx4), (Cx + xx2),(Cx - xx2), (Cx - xx4), (Cx - xx2), (Cx + xx2), (Cx + xx1), (Cx - xx1), (Cx - xx3), (Cx - xx1), (Cx + xx1), (Cx + xx3)};
      int polygonYYs [] = {(Cy), (Cy + yy2), (Cy + yy2), (Cy), (Cy - yy2), (Cy - yy2), (Cy - yy1), (Cy - yy1), (Cy), (Cy + yy1), (Cy + yy1), (Cy)};
      
      int yyy1 = (int)(Math.sin( Math.toRadians( 60 ) ) * SSS);
      int xxx1 = (int)(Math.cos( Math.toRadians( 60 ) ) * SSS);
      int yyy2 = (int)(Math.sin( Math.toRadians( 60 ) ) * SSS2);
      int xxx2 = (int)(Math.cos( Math.toRadians( 60 ) ) * SSS2);
      int xxx3 = SSS;
      int xxx4 = SSS2;
   
      int polygonXXXs [] = {(Cx + xxx4), (Cx + xxx2), (Cx - xxx2), (Cx - xxx4), (Cx - xxx2), (Cx + xxx2), (Cx + xxx1), (Cx - xxx1), (Cx - xxx3), (Cx - xxx1), (Cx + xxx1), (Cx + xxx3)};
      int polygonYYYs [] = {(Cy), (Cy + yyy2), (Cy + yyy2), (Cy), (Cy - yyy2), (Cy - yyy2), (Cy - yyy1), (Cy - yyy1), (Cy), (Cy + yyy1), (Cy + yyy1), (Cy)};

      
      int y1 = (int)(Math.sin( Math.toRadians( 60 ) ) * S);
      int x1 = (int)(Math.cos( Math.toRadians( 60 ) ) * S);
      int y2 = (int)(Math.sin( Math.toRadians( 60 ) ) * S2);
      int x2 = (int)(Math.cos( Math.toRadians( 60 ) ) * S2);
      int x3 = S;
      int x4 = S2;
   
      int polygonXs [] = {(Cx + x4), (Cx + x2),(Cx - x2), (Cx - x4), (Cx - x2), (Cx + x2), (Cx + x4), (Cx + x3), (Cx + x1), (Cx - x1), (Cx - x3), (Cx - x1), (Cx + x1), (Cx + x3)};
      int polygonYs [] = {(Cy), (Cy + y2), (Cy + y2), (Cy), (Cy - y2), (Cy - y2), (Cy), (Cy), (Cy - y1), (Cy - y1), (Cy), (Cy + y1), (Cy + y1), (Cy)};
      
      
      
      
      
      Shape shape2 = new Polygon(polygonXXs, polygonYYs, polygonXXs.length);
      Shape shape3 = new Polygon(polygonXXXs, polygonYYYs, polygonXXXs.length);
      Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);

      
      
     
       
   
      
      int circleX = triangleX_1;
      int circleY = triangleY_1;
      
      
      
      AffineTransform identity = new AffineTransform();
   
      Graphics2D g2d = (Graphics2D)hexaBuffer;
       
      g2d.setTransform(identity);
   
      clear(g2d, backgroudColor1, backgroudColor2, S, S2);
      rotate(g2d, dir);
          
      
      g2d.setColor(new Color(0, 0, 0));
      
      g2d.draw(new Line2D.Double(Cx + x3, Cy, Cx + 900, Cy));
      g2d.draw(new Line2D.Double(Cx - x3, Cy, Cx - 900, Cy));
      g2d.draw(new Line2D.Double(Cx + x2, Cy + y2, Cx + 1000, ((((y1-13)*1000)/x1) + Cx)));
      g2d.draw(new Line2D.Double(Cx - x2, Cy + y2, Cx - 1000, ((((y1-13)*1000)/x1) + Cx)));
      
      g2d.draw(new Line2D.Double(Cx + x2, Cy - y2, Cx + 1000, ((-((y1+13)*1000)/x1) + Cx)));
      g2d.draw(new Line2D.Double(Cx - x2, Cy - y2, Cx - 1000, ((-((y1+13)*1000)/x1) + Cx)));
       
      g2d.fill(shape);
      //g2d.fill(shape2);
      g2d.fill(shape3);

      g2d.fillOval((circleX - 20), (circleY - 20), (20), (20)); 
      
      //System.out.println("circleX: " + circleX);
      //System.out.println("circleY: " + circleY);
      
   
      
      repaint();
      
      
      
   }
   
   
   public void rotate(Graphics2D g2d, int dir)
   {
      g2d.rotate(Math.toRadians(r), (Cx), (Cy)); // rotates about transformed origin
      r = r + dir;
   }
   
   public void clear(Graphics2D g2d, int [] backgroudColor, int [] backgroudColor1, int S, int S2)
   {
      g2d.setColor(new Color(backgroudColor[0], backgroudColor [1], backgroudColor [2]));
      
      /*int y1 = (int)(Math.sin( Math.toRadians( 60 ) ) * S);
      int x1 = (int)(Math.cos( Math.toRadians( 60 ) ) * S);
      int y2 = (int)(Math.sin( Math.toRadians( 60 ) ) * S2);
      int x2 = (int)(Math.cos( Math.toRadians( 60 ) ) * S2);
      int x3 = S;
      int x4 = S2;*/
      
      int topCx [] = {};
      int topCy [] = {};
      
      
      
      //g2d.fillPolygon(topCx, topCy, topCx.length);
      
      g2d.fillRect(0,0,x,y);
      
      repaint();
   }
   
   
}