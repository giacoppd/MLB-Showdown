import java.util.*;
import java.io.*;

public class LineupManager {

   private LineupSlot currentBatter;
   private Set<HitterData> bench;
   private Set<HitterData> bullpen;
   private LineupSlot temp;
  // private PitcherData currentPitcher;
   
   public LineupManager(Set<HitterData> pool) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      //Scanner console = new Scanner(new File("TestingFile.txt"));
     // System.out.println("What file do you want to load?");
     // Scanner fuckYou = new Scanner(System.in);
     // Scanner console = new Scanner(new File(fuckYou.nextLine()));
      boolean[] fullPosition = new boolean[10];
      LineupSlot temp2 = new LineupSlot();
      for (int i = 1; i<= 9; i++) {
         while (!inputPlayer(console, pool, fullPosition)) {
            System.out.println("Lets Try Again");
            if (console.hasNextLine()) {
               console.nextLine();
            }
         }
         if (i == 1) {
            currentBatter = temp;
            temp2 = temp;
         } else { // (i > 1 && i <= 9) {
            temp2.next = temp;
            temp2 = temp;
         } 
         if (i == 9) {
            temp.next = currentBatter;
         }
         console.nextLine();
      }
      
   }
   
  /* public void substitute(PlayerData inLineup, PlayerData onBench) {
      while(temp.next.player != inLineup) {
         temp = temp.next;
      }
      //Remove temp.next.player from the game and replace him with bench player
   } */
   
   
  /* public void addPitcher(PitcherData pitcher) {
      bullpen.add(pitcher);
   } */
   
   private boolean addPlayer(String name, int fieldPosition, Set<HitterData> pool) {
      for (HitterData h : pool) {
         if (h.toString().toLowerCase().equals(name)) {
            if (h.playsPosition(fieldPosition)) {
               temp = new LineupSlot(h, fieldPosition);
               return true;
            } else {
               System.out.println("Invalid position (Doesn't Play)");
               return false;
            }
         }
      } 
      System.out.println("That player does not exist");
      return false;
   }
   
   private boolean inputPlayer(Scanner console, Set<HitterData> pool, boolean[] positions) {
      //System.out.println(pool);
      System.out.println("Name of Hitter? ");
      String name = console.nextLine().toLowerCase();
      if (name.equals("query")) {
         System.out.println("Player or Position?");
         String state = console.nextLine().toLowerCase();
         if (state.equals("player")) {
            query(console, pool);
         } else if (state.equals("position")) {
            queryPosition(console, pool);
         } else {
            System.out.println("you so silly, try again");
         }
         return false;
      }
      System.out.println("Position?       ");
      try {
         int fieldPosition = setPosition(console.nextLine().toLowerCase());
         if (positions[fieldPosition]) {
            System.out.println("This position is full");
            return false;
         }
         if (addPlayer(name, fieldPosition, pool)) {
            pool.remove(temp.player);
            positions[fieldPosition] = true;
         } else {
            return false;
         }
      } catch (InputMismatchException e) {
         System.out.println("Invalid Position (Not Applicable)");
         return false;
      } catch (ArrayIndexOutOfBoundsException o) {
         System.out.println("Invalid Position (Not Applicable)");
         return false;
      }
      System.out.println("Player added");
      return true;
   }
   
   public HitterData getCurrent() {
      return currentBatter.player;
   }
   
   public void next() {
      currentBatter = currentBatter.next;
   }
   
   public int getFielding(int position) {
      temp = currentBatter;
      for (int i = 1; i <= 9; i++) {
         if (temp.position == position) {
            if (temp.player.getFielding(temp.position) == -1) {
               return 0;
            }
            return temp.player.getFielding(temp.position);
         }
         temp = temp.next;
      }
      throw new IllegalArgumentException();
   }
   
   private void query(Scanner console, Set<HitterData> pool) {
      System.out.println("Name of query?");
      String name = console.nextLine ();
      for (HitterData h : pool) {
         if (h.toString().toLowerCase().equals(name.toLowerCase())) {
            h.query();
            return;
         }
      }
      System.out.println("No player with that name");
   }
   
   private void queryPosition(Scanner console, Set<HitterData> pool) {
      Map<Integer, Set<HitterData>> plaiz = new HashMap<Integer, Set<HitterData>>();
      for (HitterData h : pool) {
         for (int i = 0; i <= 9; i++) {
            if (h.playsPosition(i)) {
               if (!plaiz. containsKey(i)) {
                  plaiz.put(i, new HashSet());
               }
               plaiz.get(i).add(h);
            }
         }
      }
      System.out.println("Position to query?");
      System.out.println(plaiz.get(setPosition(console.nextLine().toLowerCase())));
   }
   
   private int setPosition(String position) {
      switch(position) {
         case "0":
         case "dh":
         case "designated hitter":
            return 0;
         case "2":
         case "c":
         case "catcher":
            return 2;
         case "3":
         case "1b":
         case "first base":
            return 3;
         case "4":
         case "2b":
         case "second base":
            return 4;
         case "5":
         case "3b":
         case "third base":
            return 5;
         case "6":
         case "ss":
         case "shortstop":
            return 6;
         case "7":
         case "lf":
         case "left field":
            return 7;
         case "8":
         case "cf":
         case "center field":
            return 8;
         case "9":
         case "rf":
         case "right field":
            return 9;
         default:
            return 11;
      }
   }    
}