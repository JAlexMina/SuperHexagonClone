import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import sun.audio.*;
import java.io.*;



public class SuperHexaSweeperDriver
{
   
  
   public static void main(String[] args)
   {
      
      Time t = new Time();
      double time = 0; 
      long time2 = 0;
      
      
      
      
      JFrame frame = new JFrame("Super Hexa-Sweeper, Ralex inc.");      
      frame.setSize(1280, 720);       
      frame.setLocation(160, 90);       
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
   
      
      hexaPanel theGame = new hexaPanel();      
      
   
      frame.setContentPane(theGame);
      frame.setVisible(true);
   
      
      
      double followTime = 0;
      int speed = 7; //Lower is faster
   
      

      int SS = 900;

      int dir = -1;
      int S = 80;

      int tS = 108;
      long cO = 0;
      
      int backgroudColor1 [] = {100, 100, 100};
      int backgroudColor2 [] = {200, 200, 200};
      boolean newObstacle = true;
      //music();
      
      
     
      while (true)
      {
         time = ((t.getAgeInMilliSeconds(speed)));
         time2 = t.getAgeInMilliSeconds(1);
         
         
              
         if(followTime != time)
         {
            theGame.run(newObstacle, SS, dir, S, tS, backgroudColor1, backgroudColor2);
            
            if(SS > S)
            {
               SS = SS - 5;
               newObstacle = false;
            }
            else
            {
               SS = 900;
               newObstacle = true;
            }
            

            
            
            //System.out.println(time2/1000);
            followTime = time; 
            
            if ((time2/1000) % 2 == 0)
            {
               //tS++;
               //S++;
               //S2++;
               //SS++;
               //SS2++;
               
               theGame.setTS(tS);
               
               backgroudColor1 [0] = backgroudColor1 [1] = backgroudColor1 [2] = 200;
               backgroudColor2 [0] = backgroudColor2 [1] = backgroudColor2 [2] = 100;
               
            }
            else if ((time2/1000) % 2 == 1)
            {
               //tS--;
               //S--;
               //S2--;
               //SS--;
               //SS2--;
               
               theGame.setTS(tS);
               
               backgroudColor1 [0] = backgroudColor1 [1] = backgroudColor1 [2] = 100;
               backgroudColor2 [0] = backgroudColor2 [1] = backgroudColor2 [2] = 200;
            }
            
            
            if ((time2/1000) % 5 == 0 && cO != time2/1000)
            {
               dir = dir * (-1);
               cO = time2/1000;
            }     
            
             
         }
          
         
      }
   
      
      
            
   
         
   }
   
   
//    public static void music()
//    {
//       AudioPlayer MGP = AudioPlayer.player;
//       AudioStream BGM;
//       AudioData MD;
//       ContinuousAudioDataStream loop = null;
//      
//      
//       try
//       {
//          BGM = new AudioStream(new FileInputStream("song__.wav"));
//          MD = BGM.getData();
//          loop = new ContinuousAudioDataStream(MD);
//       }
//       catch(IOException error){
//          System.out.println("CannotFindAudio");
//       }
//      
//      
//       MGP.start(loop);
//      
//    } 
     
  
}
         
   
   
   
      
   
         
 
