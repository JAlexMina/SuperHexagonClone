public class Time
{
   //do nothing constructor
   public Time(){}
   
   //start time (in milliseconds)
   private final long createdMillis = System.currentTimeMillis();
   
   //method to get the age of the program in milliseconds   
   public long getAgeInMilliSeconds(int speed) 
   {
      long nowMillis = System.currentTimeMillis()/speed;
      return (nowMillis - this.createdMillis);
   }   
}