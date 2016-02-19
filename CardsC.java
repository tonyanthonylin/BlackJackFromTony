import java.util.*;
import java.util.Scanner;

public class CardsC{
  
  static int count=52; //the count represents the number of cards remaining in the deck
  
  public static int rand(int high){
      return (int) (high*Math.random()+1);
    }
  
  public static void shuffle(String[] the_deck, int switches){
    String temp;
    int a; int b;
    for(int i=0; i<switches; i++){
      a = rand(52);
      b = rand(52);
      temp = the_deck[a-1];
      the_deck[a-1] = the_deck[b-1];
      the_deck[b-1] = temp;
    } 
  }
  
  public static String deal(String[] the_deck){
    count=count-1;    
    return the_deck[count];}
  
  public static int aces(String the_card){
   if(the_card.charAt(0)=='A'){
       return 1;}
     else{
       return 0;}
   }
  
  public static int aces(String[] the_hand){
   int sum=0;
   for(int i=0; i<the_hand.length;i++){
     sum = sum + aces(the_hand[i]);
   }
   return sum;
 }
  
  public static int aces(ArrayList the_hand){
   int sum=0;
   for(int i=0; i<the_hand.size();i++){
     sum = sum + aces(the_hand.get(i).toString());
   }
   return sum;
 }
  
 public static int value(String the_card){
   char first = the_card.charAt(0);
   if (first=='1'|first=='J'|first=='Q'|first=='K'){
     return 10;
   }
     else if(first=='A'){
       return 11;}
     else{
       return Character.getNumericValue(first);
     }
   }
 
 public static int value(String[] the_hand){
   int sum=0;
   for(int i=0; i<the_hand.length;i++){
     sum = sum + value(the_hand[i]);
   }
   return sum;
 }
 
 public static int value(ArrayList the_hand){
   int sum=0;
   int num_aces=aces(the_hand);
   for(int i=0; i<the_hand.size();i++){
     sum = sum + value(the_hand.get(i).toString());
   }
   while(num_aces>0 && sum>21){
     sum=sum-10;
     num_aces=num_aces-1;
   }
   return sum;
 }
   
   
  
  public static void main(String[] args){
   
    Scanner scan = new Scanner(System.in);
    
    String[] deck = new String[52];
    String[] suit = new String[4];
    int[] card = new int[13];
    
    for (int i=0; i<card.length; i++){
      card[i]=i+1;}
    String cardName;
    suit[0] = "Clubs";
    suit[1] = "Diamonds";
    suit[2] = "Hearts" ;
    suit[3] = "Spades";
      
      
    for(int i=0; i<4; i++){
      for(int j=0; j<13; j++){
        if(j==0){cardName="Ace";}
        else if(j==10){cardName="Jack";}
        else if(j==11){cardName="Queen";}
        else if(j==12){cardName="King";}
        else {cardName=Integer.toString(card[j]);}
        deck[ 13*i+j ]= cardName + "_" +suit[i];
      }
    }
 /*     
    for(int i=0; i<52; i++){
        System.out.println(deck[i]);
     }
   
   shuffle(deck, 1000);
    
    System.out.println("SHUFFLED");
    
    for(int i=0; i<52; i++){
        System.out.println(deck[i]);
      }
    
    System.out.println("DEAL");
 */   
    //String[] delt = new String[2];
    //delt[0]=deal(deck);
    //delt[1]=deal(deck);
    
   // System.out.println(delt[0] + " and " + delt[1] + " and " + count + " cards remaining.");
  shuffle(deck, 1000);
    
    String say;
  boolean state=true;
    
    ArrayList hand = new ArrayList();
    ArrayList dealer_hand = new ArrayList();
    dealer_hand.add( deal(deck) );
    dealer_hand.add( deal(deck) );
    hand.add( deal(deck) );
    
    while(state){
      
    hand.add( deal(deck) );
    
    System.out.println("Dealer showing: " + dealer_hand.get(1));
    System.out.println("Contents of hand: " + hand);
    System.out.println("Your score is: " + value(hand));
    
    if(value(hand)>21){
      System.out.println("BUST!!!!");
      break;
    }
    
    System.out.println( "hit[H] or stand[S]?");
         say=scan.nextLine();
         if(say.equals("H")){state=true;}
           if(say.equals("S")){state=false;}
         else{System.out.println("why the hell did you put this?!");}
    }
    
    while( value(dealer_hand)<17 ){
      dealer_hand.add( deal(deck) );
    }
      
    
    System.out.println("Dealer has: " + dealer_hand);
    System.out.println("Dealer score is: " + value(dealer_hand));
    
    if( (value(hand)>value(dealer_hand) && value(hand)<22) | (value(dealer_hand) > 21) ){
      System.out.println( "YOU WIN !!!!");
    }
    else{System.out.println( "YOU LOSE. BOO !!!!");}
    
    
    
    
   // System.out.println(hand.get(0) + " and " + hand.get(1) + " and " + count + " cards remaining.");
    
   // hand.add( deal(deck) );
    
    
   // System.out.println(value(hand));
  }
}