
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathf
 */
public class Main {
    static int playerSum;
    static int dealerSum;
    static int bet;
    static int totalCash;
    static boolean failDoubleDown = false;
    //static Scanner Keyboard = new Scanner(System.in);
        
             
    static PrintStream Screen = new PrintStream(System.out);
    static Scanner Keyboard = new Scanner (System.in);
    
    //Array for cards the player has down on table
    static ArrayList<String> playerCards = new ArrayList<>();
        
    //Array for dealer cards dealer has on table
    static ArrayList<String> dealerCards = new ArrayList<>();
    
    static Thread thread = new Thread();
    static Player player = new Player();
    static Dealer cpu = new Dealer();
    public static void resetCards(){
        player.getCards().resetCards();
        playerCards.clear();
        dealerCards.clear();
    }
    public static void waitTime(int myWaitTime){
        try {
            Thread.sleep(myWaitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void placeBuffer(){
        Screen.println("---------------------------------");
    }
    public static void displayCardsAndValues(){
        //Print dealer cards
        Screen.print("The dealers cards are");
        Screen.print(dealerCards);
        cpu.getCards().computeValue(dealerCards);
        Screen.println(cpu.getCards().getCardsValue());
        
        //Print player cards
        player.getCards().computeValue(playerCards);
        Screen.print("Your cards are");
        Screen.print(playerCards);
        Screen.println(player.getCards().getCardsValue());
        playerSum = player.getCards().getCardsValue();
        //checkPlayerOver();
    }
    public static void dealerPlays(){
        player.getCards().computeValue(playerCards);
        cpu.getCards().computeValue(dealerCards);
        dealerSum = cpu.getCards().getCardsValue();
        playerSum = player.getCards().getCardsValue();
        
        //boolean failDoubleDown = false;
        //player double downs but card goes over 21
        if(playerSum > 21){
            failDoubleDown = true;
            checkPlayerOver();
        }
        
        
        
        //Player stays and dealer wins
        if((dealerSum > playerSum) && (dealerSum <= 21)){
            Screen.println("The dealer wins");
            displayCardsAndValues();
            Screen.printf("You had %d",playerSum);
            Screen.printf(" the dealer had %d", dealerSum);
            Screen.print("\n");
            Screen.print("You lost $");
            Screen.print(player.getMoney().getBet());
            Screen.print("\n");
            //Total - bet;
            player.getMoney().setTotalCash(player.getMoney().getTotalCash() - player.getMoney().getBet());
            Screen.print("Your total cash is $");
            Screen.print(player.getMoney().getTotalCash());
            Screen.print("\n");
            resetCards();
            askPlayAgain();
        }
        //player stay,  player and dealer tie value
        else if (dealerSum == playerSum){
            Screen.printf("Its a draw!\n You both had %d",dealerSum);
            Screen.print("\n");
            Screen.print("Your total cash is $");
            Screen.print(player.getMoney().getTotalCash());
            displayCardsAndValues();
            resetCards();
            askPlayAgain();
        }
        while(dealerSum <= playerSum){
            waitTime(1000);
            dealerCards.add(cpu.getCards().drawCard());
            cpu.getCards().computeValue(dealerCards);
            dealerSum = cpu.getCards().getCardsValue();
        
            Screen.print("The dealers cards are");
            Screen.print(dealerCards);
            Screen.println(cpu.getCards().getCardsValue());
            waitTime(3000);
            
            
            
            //player stays, dealer is drawing to win but ties
            if (dealerSum == playerSum){
                Screen.printf("Its a draw!\n You both had %d",dealerSum);
                Screen.print("\n");
                Screen.print("Your total cash is $");
                Screen.print(player.getMoney().getTotalCash());
                Screen.print("\n");
                displayCardsAndValues();
                resetCards();
                askPlayAgain();
                break;
            }
        }
        //while loop ends, player stays, dealer drew and won
        if((dealerSum > playerSum) && (dealerSum <= 21)){
            Screen.println("The dealer wins");
            displayCardsAndValues();
            Screen.printf("You had %d",playerSum);
            Screen.printf(" the dealer had %d", dealerSum);
            Screen.print("\n");
            Screen.print("You lost $");
            Screen.print(player.getMoney().getBet());
            Screen.print("\n");
            //Total - bet;
            player.getMoney().setTotalCash(player.getMoney().getTotalCash() - player.getMoney().getBet());
            Screen.print("Your total cash is $");
            Screen.print(player.getMoney().getTotalCash());
            Screen.print("\n");
            resetCards();
            askPlayAgain();
        }
        //while loop ends, player styas, dealer draws and goes over 21
        if(dealerSum > 21){
            Screen.printf("You Win $%d",player.getMoney().getBet());
            Screen.print("\n");
            //Money += bet
            player.getMoney().setTotalCash(player.getMoney().getTotalCash() + player.getMoney().getBet());
            Screen.print("Your total cash is $");
            Screen.print(player.getMoney().getTotalCash());
            Screen.print("\n");
            Screen.print("Your cards are");
            Screen.print(playerCards);
            Screen.println(player.getCards().getCardsValue());
            resetCards();
            askPlayAgain();
        }
        
    }
    public static void askPlayAgain(){
        placeBuffer();
        Screen.println("Would you like to continue playing?");
        Screen.println("1. Yes\n 2. No");
        int choice = Keyboard.nextInt();
        if (choice == 1){
            beginRound();
        }
        if (choice ==2){
            Screen.print("Bye");
            System.exit(0);
        }
        
    }
    public static void checkPlayerOver(){
        if(playerSum > 21){
            Screen.println("You went over 21");
            Screen.print("You lost $");
            Screen.print(player.getMoney().getBet());
            Screen.print("\n");
            //Total - bet;
            player.getMoney().setTotalCash(player.getMoney().getTotalCash() - player.getMoney().getBet());
            Screen.print("Your total cash is $");
            Screen.print(player.getMoney().getTotalCash());
            Screen.print("\n");
            resetCards();
            askPlayAgain();
        }
        else{
            playerChoiceSelection();
        }
        
        
    }
    public static void askPlayerBet(){
        Screen.print("\n");
        Screen.printf("You have $ %s",player.getMoney().getTotalCash());
        Screen.println("\n Please enter your bid: $ ");
        bet = Keyboard.nextInt();
        Screen.printf("\n");
        player.getMoney().setBet(bet);
        while((player.getMoney().getBet()) > (player.getMoney().getTotalCash())){
            Screen.print("\n Your bet is higher tahn your total cash, please re-enter\n");
            bet = Keyboard.nextInt();
            player.getMoney().setBet(bet);
        }
        
    }
    public static void playerHit(){
        //Print dealer cards
        Screen.print("The dealers cards are");
        Screen.print(dealerCards);
        cpu.getCards().computeValue(dealerCards);
        Screen.println(cpu.getCards().getCardsValue());
        
        //Print player cards
        playerCards.add(player.getCards().drawCard());
        player.getCards().computeValue(playerCards);
        Screen.print("Your cards are");
        Screen.print(playerCards);
        Screen.println(player.getCards().getCardsValue());
        playerSum = player.getCards().getCardsValue();
        checkPlayerOver();
        
        
        
    }
    public static int playerChoiceSelection(){
        //Scanner Keyboard = new Scanner(System.in);
        System.out.println("1. Hit");
        System.out.println("2. Stay");
        System.out.println("3. Double down");
        int selection = Keyboard.nextInt();
        while((selection < 0)|| (selection > 3)){
            System.out.println("Invalid choice");
            selection = Keyboard.nextInt();
        }
        while(selection == 3 && ((player.getMoney().getTotalCash()) < (player.getMoney().getBet() * 2)) ){
            if((player.getMoney().getTotalCash()) < (player.getMoney().getBet() * 2)){
                System.out.println("Your bet is too high to double down, it exceededs your total cash");
                while((selection < 0)|| (selection > 2)){
                    System.out.println("Invalid choice");
                    selection = Keyboard.nextInt();
                }
            }
        }
        placeBuffer();
        if(selection == 1){
            playerHit();
        }
        if(selection == 2){
            dealerPlays();
            
        }
        if(selection == 3){
            waitTime(1000);
            playerCards.add(player.getCards().drawCard());
            Screen.print("Your cards are");
            Screen.print(playerCards);
            player.getCards().computeValue(playerCards);
            Screen.println(player.getCards().getCardsValue());
            playerSum = player.getCards().getCardsValue();
            player.getMoney().setBet(player.getMoney().getBet() * 2);
            waitTime(1000);
            dealerPlays();
        }
        
        return selection;
        
        
    }
    public static void beginRound(){
        askPlayerBet();
        
        //draw two cards for dealer
        dealerCards.add(cpu.getCards().drawCard());
        dealerCards.add(cpu.getCards().drawCard());
        
        
        Screen.print("The dealers cards are");
        Screen.print(dealerCards);
        cpu.getCards().computeValue(dealerCards);
        Screen.println(cpu.getCards().getCardsValue());
        
        //draw cards to players hand
        playerCards.add(player.getCards().drawCard());
        playerCards.add(player.getCards().drawCard());
        
        
        Screen.print("Your cards are");
        Screen.print(playerCards);
        player.getCards().computeValue(playerCards);
        Screen.println(player.getCards().getCardsValue());
        
        playerChoiceSelection();    
    }
    public static void main(String[] args) {
        
        Screen.println("Hello, please enter your name");
        String inputName = Keyboard.nextLine();
        Screen.println("Please enter your Bank amount");
        totalCash = Keyboard.nextInt();
        
        player.setName(inputName);
        player.getMoney().setTotalCash(totalCash);
        
        beginRound();
       
        /*String ln = "a";
        if((ln == "a") || (ln == "b") || (ln == "c") || (ln == "d")){
            // do calculation for Torus chape
        }
        */
    }
    
}
