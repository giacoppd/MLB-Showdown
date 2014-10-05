import java.util.*;

public class Field {

   private BaseSlot first;
   private BaseSlot second;
   private BaseSlot third;
   private Random r;
   
   public Field() {
      third = new BaseSlot(null);
      second = new BaseSlot(third);
      first = new BaseSlot(second);
      r = new Random();
   }
   
   public GameStat single(HitterData onCard, GameStat track) {
      if (!third.isEmpty()) {
         third.onBase = null;
         track.runs++;
      }
      if (!second.isEmpty()) {
         third.onBase = second.onBase;
         second.onBase = null;
      }
      if (!first.isEmpty()) {
         second.onBase = first.onBase;
      }
      first.onBase = onCard;
      return track;       
   }
   
   public GameStat twoBase(HitterData onCard, GameStat track) {
      if (!third.isEmpty()) {
         third.onBase = null;
         track.runs++;
      }
      if (!second.isEmpty()) {
         second.onBase = null;
         track.runs++;
      }
      if (!first.isEmpty()) {
         third.onBase = first.onBase;
         first.onBase = null;
      }
      second.onBase = onCard;
      return track; 
   }
   
   public GameStat triple(HitterData onCard, GameStat track) {
      if (!third.isEmpty()) {
         third.onBase = null;
         track.runs++;
      }
      if (!second.isEmpty()) {
         second.onBase = null;
         track.runs++;
      }
      if (!first.isEmpty()) {
         first.onBase = null;
         track.runs++;
      }
      third.onBase = onCard;
      return track; 
   }
   
   public GameStat homer(GameStat track) {
      track.runs++;
      if (!third.isEmpty()) {
         third.onBase = null;
         track.runs++;
      }
      if (!second.isEmpty()) {
         second.onBase = null;
         track.runs++;
      }
      if (!first.isEmpty()) {
         first.onBase = null;
         track.runs++;
      }
      return track;
   }
   
   public GameStat walk(HitterData onCard, GameStat track) {
      if (!first.isEmpty()) {
         if (!second.isEmpty()) {
            if (!third.isEmpty()) {
               track.runs++;
            }
            third.onBase = second.onBase;
         }
         second.onBase = first.onBase;
      }
      first.onBase = onCard;
      return track;
   }
      
   public GameStat singlePlus(HitterData onCard, GameStat track) {
      track = single(onCard, track);
      if (second.isEmpty()) {
         second.onBase = first.onBase;
         first.onBase = null;
      }
      return track;
   }
   
   public GameStat groundout(LineupManager fielder, HitterData atBat, 
                             GameStat track) {
      track.outs++;
      if (checkOuts(track)) {
         return track;
      }
      if (first.onBase != null) {
         if (infieldCheck(fielder, atBat)) {
            track.outs++;
            if (checkOuts(track)) {
               return track;
            }
            first.onBase = null;
         } else {
            first.onBase = atBat;
         }
      }
      if (third.onBase != null) {
         track.runs++;
         third.onBase = null;
      }
      if (second.onBase != null) {
         third.onBase = second.onBase;
         second.onBase = null;
      }     
      return track;
   }
   
   public GameStat strikeout(GameStat track) {
      track.outs++;
      return track;
   }
   
   public GameStat flyout(GameStat track) {
      track.outs++;
      return track;
   }
   
   public GameStat popout(GameStat track) {
      track.outs++;
      return track;
   }
   
   public void getState() {
      if (first.onBase != null) {
         System.out.println(first.onBase + " is on first");
      }
      if (second.onBase != null) {
         System.out.println(second.onBase + " is on second");
      }
      if (third.onBase != null) {
         System.out.println(third.onBase + " is on third");
      }
   }
   
   public void clear() {
      first.onBase = null;
      second.onBase = null;
      third.onBase = null;
   }
   
   private class BaseSlot {
   
      public HitterData onBase;
      private BaseSlot nextBase;
   
      private BaseSlot(BaseSlot nextBase) {
         onBase = null;
         this.nextBase = nextBase;
      }
      
      private boolean isEmpty() {
         return onBase == null;
      }
   }
   
   //Wrong
   
   private boolean catcherCheck(LineupManager teamOne, HitterData runner) {
      int total = r.nextInt(20) + 1;
      total += teamOne.getFielding(2);   
      return total > runner.getSpeed();
   }
   
   //Check for double play
   
   private boolean infieldCheck(LineupManager teamOne, HitterData runner) {
      int total = r.nextInt(20) + 1;
      for (int i = 3; i <= 6; i++) {
         total += teamOne.getFielding(i);
      }
      return total > runner.getSpeed();
   }
   
   private boolean checkOuts(GameStat track) {
      return track.outs == 3;
   }
}