import java.util.*;

public class HitterData extends PlayerData {

   private Range singlePlus;
   private Range triple;
   private int speed;
   private String battingSide;
   
   public HitterData(Scanner input) {
      setLastName(input.next());
      setFirstName(input.next());
      for (int i = 0; i <= 9; i++) {
         setPosition(input.nextInt(), i);
      }
      battingSide = input.next();
      setBaseMod(input.nextInt());
      speed = input.nextInt();
      setStrikeout(new Range(input.nextInt(), input.nextInt()));
      setGroundout(new Range(input.nextInt(), input.nextInt()));
      setFlyout(new Range(input.nextInt(), input.nextInt()));
      setWalk(new Range(input.nextInt(), input.nextInt()));
      setSingle(new Range(input.nextInt(), input.nextInt()));
      singlePlus = new Range(input.nextInt(), input.nextInt());
      setDouble(new Range(input.nextInt(), input.nextInt()));
      triple = new Range(input.nextInt(), input.nextInt());
      setHomer(new Range(input.nextInt(), input.nextInt()));
   }
   
   public Range getSinglePlus() {
      return singlePlus;
   }
   
   public Range getTriple() {
      return triple;
   }
   
   public int getSpeed() {
      return speed;
   }
   
   public GameStat checkCard(Field grass, int dice, HitterData batter, GameStat track,
                             LineupManager enemy) {
      track = super.checkCard(grass, dice, batter, track, enemy);
      if (singlePlus.inRange(dice)) {
         track = grass.singlePlus(this, track);
         System.out.println(toString() + " is going for extras!");
      } else if (triple.inRange(dice)) {
         track = grass.triple(this, track);
         System.out.println("A THREE BAGGER");
      }
      return track;
   }
}