public class Time
{
   public Time()
   {
   }
   
   
   private final double createdMillis = System.currentTimeMillis();
   
   private final long createdMillis2 = System.currentTimeMillis();

   public double getAgeInDeci() 
   {
      double nowMillis = System.currentTimeMillis();
      return (nowMillis - this.createdMillis);
   }
   
   public long getAgeInMiliSeconds(int speed) 
   {
      long nowMillis2 = System.currentTimeMillis()/speed;
      return (nowMillis2 - this.createdMillis2);
   }
   
   
   
}