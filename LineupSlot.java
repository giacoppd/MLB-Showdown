public class LineupSlot {
   
   public HitterData player;
   public int position; //Correspond to batter's position
   public LineupSlot next;
   
   public LineupSlot(HitterData p, int i) {
      player = p;
      position = i;
      next = null;
   }
   
   public LineupSlot() {
   }
}