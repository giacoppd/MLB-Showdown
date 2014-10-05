import java.util.*;

public class PitcherData extends PlayerData {

   private Range popout;
   private int innings;
   private String role;
   private String hand;
   
   public PitcherData(Scanner input) {
      setLastName(input.next());
      setFirstName(input.next());
      for (int i = 0; i <= 9; i++) {
         setPosition(input.nextInt(), i);
      }
      setBaseMod(input.nextInt());
      role = input.next();
      hand = input.next();
      innings = input.nextInt();
      popout = new Range(input.nextInt(), input.nextInt());
      setStrikeout(new Range(input.nextInt(), input.nextInt()));
      setGroundout(new Range(input.nextInt(), input.nextInt()));
      setFlyout(new Range(input.nextInt(), input.nextInt()));
      setWalk(new Range(input.nextInt(), input.nextInt()));
      setSingle(new Range(input.nextInt(), input.nextInt()));
      setDouble(new Range(input.nextInt(), input.nextInt()));
      setHomer(new Range(input.nextInt(), input.nextInt()));
   }
   
   public GameStat checkCard(Field grass, int dice, HitterData batter, GameStat track,
                             LineupManager enemy) {
      track = super.checkCard(grass, dice, batter, track, enemy);
      if (popout.inRange(dice)) {
         track = grass.popout(track);
         System.out.println("A can of corn");
      }
      return track;
   }
   
   public int checkInnings(int soFar) {
      if (soFar > innings) {
         return innings - soFar;
      }
      return 0;
   }

}