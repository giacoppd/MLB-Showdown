import java.util.*;

public class PlayerData {

   private String firstName;
   private String lastName;
   private Range strikeout;
   private Range groundout;
   private Range flyout;
   private Range walk;
   private Range single;
   private Range twobase;
   private Range homer;
   private int[] positions = new int[10]; //Follow scorecard format, 1=P, 2=C, 3=1B, etc, 0=DH
   private int baseMod; //On-Base/Control
   //Uncomment to implement  private int cost;
   
   public int getBaseMod() {
      return baseMod;
   }
   
   public Range getStrikeout() {
      return strikeout;
   }
   
   public Range getGroundout() {
      return groundout;
   }
   
   public Range getFlyout() {
      return flyout;
   }
   
   public Range getWalk() {
      return walk;
   }
   
   public Range getSingle() {
      return single;
   }
   
   public Range getDouble() {
      return twobase;
   }
   
   public Range getHomer() {
      return homer;
   }
   
   public void setFirstName(String s) {
      firstName = s;
   }
   
   public void setLastName(String s) {
      lastName = s;
   }
   
   public void setStrikeout(Range r) {
      strikeout = r;
   }
   
   public void setGroundout(Range r) {
      groundout = r;
   }  
   
   public void setFlyout(Range r) {
      flyout = r;
   }
   
   public void setWalk(Range r) {
      walk = r;
   }
   
   public void setSingle(Range r) {
      single = r;
   }
   
   public void setDouble(Range r) {
      twobase = r;
   }
   
   public void setHomer(Range r) {
      homer = r;
   }
   
   public void setPosition(int val, int index) {
      positions[index] = val;
   }
   
   public void setBaseMod(int i) {
      baseMod = i;
   }
   
   public String toString() {
      return firstName + " " + lastName;
   }
   
   public boolean playsPosition(int i) {
      if (i == 0 && positions[1] == 0) {
         return true;
      }
      return positions[i] != 0;
   }
   
   public void query() {
      String plays = "";
      for(int i = 0; i <= 9; i++) {
         if (positions[i] != 0) {
            if (plays.isEmpty()) {
               plays += i;
            } else {
               plays += ", " + i;
            }
         }
      }
      System.out.println(this + " plays: " + plays);           
   }
   
   public int getFielding(int position) {
      return positions[position];
   }
   
   public GameStat checkCard(Field grass, int dice, HitterData batter, GameStat track,
                             LineupManager enemy) {
      if (strikeout.inRange(dice)) {
         track = grass.strikeout(track);
         System.out.println("SWING AND A MISS");
      } else if (groundout.inRange(dice)) {
         track = grass.groundout(enemy, batter, track);
         System.out.println("Slow dribbler for the out");
      } else if (flyout.inRange(dice)) {
         track = grass.flyout(track);
         System.out.println("Caught on the track");
      } else if (walk.inRange(dice)) {
         track = grass.walk(batter, track);
         System.out.println("can't even hit the zone");
      } else if (single.inRange(dice)) {
         track = grass.single(batter, track);
         System.out.println("Weakest hit I've seen");
      } else if (twobase.inRange(dice)) {
         track = grass.twoBase(batter, track);
         System.out.println("DAY TRIPPA");
      } else if (homer.inRange(dice)) {
         track = grass.homer(track);
         System.out.println("THE SLAM JAM");
      }
      return track;
   }

}