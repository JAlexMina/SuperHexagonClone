import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;
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
      long prev_beatNumber = 0;
   
      

      int SS = 900;

      int dir = -1;
      int S = 80;

      int tS = 108;
      boolean cO = false;
      
      int backgroudColor1 [] = {100, 100, 100};
      int backgroudColor2 [] = {200, 200, 200};
      boolean newObstacle = true;
      boolean bounce = false;
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
            
            int bpm = 90; // beats per minute
            long beatLength = 60000 / bpm;      // ms per beat
            long beatNumber = time2 / beatLength; 
            
            if (beatNumber % 2 == 0)
            {
               // tS++;
//                S++;
// 
//                SS++;

               
               theGame.setTS(tS);
               
               backgroudColor1 [0] = backgroudColor1 [1] = backgroudColor1 [2] = 200;
               backgroudColor2 [0] = backgroudColor2 [1] = backgroudColor2 [2] = 100;
               
            }
            else
            {
//                tS--;
//                S--;
// 
//                SS--;

               
               theGame.setTS(tS);
               
               backgroudColor1 [0] = backgroudColor1 [1] = backgroudColor1 [2] = 100;
               backgroudColor2 [0] = backgroudColor2 [1] = backgroudColor2 [2] = 200;
            }
            
            
            if ((beatNumber % 5 == 0 && beatNumber % 15 != 0) && beatNumber != prev_beatNumber) // normal direction switch
            {
               dir = Math.clamp(dir * (-1), -1, 1);
               cO = false;
            }            
             else if (beatNumber % 15 == 0 && beatNumber != prev_beatNumber) // hyper speed directions switch
            {
               dir = Math.clamp(dir * (-2), -2, 2);
               cO = true;
            }
            else if (beatNumber % 3 == 0 && cO && beatNumber != prev_beatNumber) // hyper speed direction switch on beat catch
            {
               dir = Math.clamp(dir * (-1), -1, 1);
               cO = false;
            }

            prev_beatNumber = beatNumber;      
            
             
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
         
   
   
   
      
   
         
 
