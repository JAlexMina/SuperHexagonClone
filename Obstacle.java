import java.util.ArrayList;
import java.util.Random;


public class Obstacle
{
   ArrayList<int[]> obstacleListXs = new ArrayList<>();
   ArrayList<int[]> obstacleListYs = new ArrayList<>();
   int obstacleWidth = 30;
   int currentObstacle = 0;
   int objRotation = 0;
   double x1 = 0.5; //( Math.cos( Math.toRadians( 60 )) );
   double y1 = 0.866025403784; //( Math.sin( Math.toRadians( 60 )) );
   int m_SS;
   
   // allowed rotation angles in degrees
   int[] angles = {60, 120, 180, 240, 300};
     
   public Obstacle(int SS, int S, int CenterPointx, int CenterPointy)
   {
         m_SS = SS;
         Random random = new Random();
         currentObstacle = random.nextInt(5) + 1; // Generates a number from 1 to 3     
           
         update(S, CenterPointx, CenterPointy);
      
         //rotate obstacles randomly
         
         objRotation = random.nextInt(angles.length);               
   }
   
   public boolean update(int S, int CenterPointx, int CenterPointy)
   {           
         m_SS -= 5;
         if(m_SS <= S)
         {
            return false;
         }
         
         obstacleListXs = new ArrayList<>();
         obstacleListYs = new ArrayList<>();
         
         int SS_plus_OW = m_SS + obstacleWidth;
         
         int obst_xDist1 = (int)(x1*m_SS);
         int obst_xDist2 = (int)(x1*SS_plus_OW);
         
         int obst_yDist1 = (int)(y1*m_SS);
         int obst_yDist2 = (int)(y1*SS_plus_OW);

   
         if(currentObstacle == 1)
         {
            ///////// (4 side) /////////
            int obstacleXs [] = {(CenterPointx + m_SS),          (CenterPointx + SS_plus_OW),
                                  (CenterPointx + obst_xDist2), (CenterPointx - obst_xDist2), 
                                  (CenterPointx - SS_plus_OW),  (CenterPointx - obst_xDist2), 
                                  (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1), 
                                  (CenterPointx - obst_xDist1), (CenterPointx - m_SS), 
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
            int obstacleXs [] = {(CenterPointx + m_SS),          (CenterPointx + SS_plus_OW),
                                 (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                                 
            int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                                 (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs);
            obstacleListYs.add(obstacleYs);
         }
         else if(currentObstacle == 3)
         {                 
            ///////// (2 side opposite) /////////
            int obstacleXs [] = {(CenterPointx + m_SS),          (CenterPointx + SS_plus_OW),
                                 (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                                 
            int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                                 (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs);
            obstacleListYs.add(obstacleYs);
            
            int obstacleXs2 [] = {(CenterPointx - m_SS),          (CenterPointx - SS_plus_OW),
                                  (CenterPointx - obst_xDist2), (CenterPointx - obst_xDist1)};
                                 
            int obstacleYs2 [] = {(CenterPointy),                (CenterPointy), 
                                  (CenterPointy - obst_yDist2 ), (CenterPointy - obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs2);
            obstacleListYs.add(obstacleYs2);
         }
         else if(currentObstacle == 4)
         {                 
            ///////// (3 side opposite) /////////
            int obstacleXs [] = {(CenterPointx + m_SS),          (CenterPointx + SS_plus_OW),
                                 (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                                 
            int obstacleYs [] = {(CenterPointy),                (CenterPointy), 
                                 (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs);
            obstacleListYs.add(obstacleYs);
            
            int obstacleXs2 [] = {(CenterPointx - m_SS),          (CenterPointx - SS_plus_OW),
                                  (CenterPointx - obst_xDist2), (CenterPointx - obst_xDist1)};
                                 
            int obstacleYs2 [] = {(CenterPointy),                (CenterPointy), 
                                  (CenterPointy + obst_yDist2 ), (CenterPointy + obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs2);
            obstacleListYs.add(obstacleYs2);
            
            int obstacleXs3 [] = {(CenterPointx - obst_xDist1), (CenterPointx - obst_xDist2),
                                  (CenterPointx + obst_xDist2), (CenterPointx + obst_xDist1)};
                                 
            int obstacleYs3 [] = {(CenterPointy - obst_yDist1),  (CenterPointy - obst_yDist2), 
                                  (CenterPointy - obst_yDist2 ), (CenterPointy - obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs3);
            obstacleListYs.add(obstacleYs3);
         }
         else if(currentObstacle == 5)
         {                 
            ///////// (half side) /////////
            int obstacleXs [] = {
                                   (CenterPointx - obst_xDist2), (CenterPointx - SS_plus_OW),  
                                   (CenterPointx - obst_xDist2), (CenterPointx + obst_xDist2), 
                                   (CenterPointx + obst_xDist1), (CenterPointx - obst_xDist1), 
                                   (CenterPointx - m_SS),          (CenterPointx - obst_xDist1)};
                                 
            int obstacleYs [] = { (CenterPointy + obst_yDist2),  (CenterPointy),
                                  (CenterPointy - obst_yDist2 ), (CenterPointy - obst_yDist2),  
                                  (CenterPointy - obst_yDist1),  (CenterPointy - obst_yDist1),  
                                  (CenterPointy),                (CenterPointy + obst_yDist1)};
                                  
            obstacleListXs.add(obstacleXs);
            obstacleListYs.add(obstacleYs);
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
         
               // translate back and store
               obstacleListXs.get(i)[j] = (int) Math.round(CenterPointx + rotatedX);
               obstacleListYs.get(i)[j] = (int) Math.round(CenterPointy + rotatedY);      
            }
         }
         
         
         
         return true;      
   }




}