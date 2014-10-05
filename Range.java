public class Range {
   
   private int low;
   private int high;
   
   public Range(int low, int high) {
      this.low = low;
      this.high = high;
   }
   
   public boolean inRange(int roll) {
      if (low != 0) {
         return (roll >= low && roll <= high);
      }
      return false;
   }
   
   public String toString() {
      return low + " - " + high;
   }
   
}