
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathf
 */
public class Cards {
    private int cardValue;

    private Random random;
    //private String tempCardsList[];
    private static ArrayList<String> cardsList;
    private static ArrayList<String> cardsTrash;
    ArrayList<String> trash = new ArrayList<>();
    
    Cards(){
        random = new Random();
        cardsList = new ArrayList<>();
        cardsTrash = new ArrayList<>();
        String[] tempCardsList = {"Ace-Spades","Ace-Clubs","Ace-Hearts","Ace-Diamonds",
             "2-Spades","2-Clubs","2-Hearts","2-Diamonds",
             "3-Spades","3-Clubs","3-Hearts","3-Diamonds",
             "4-Spades","4-Clubs","4-Hearts","4-Diamonds",
             "5-Spades","5-Clubs","5-Hearts","5-Diamonds",
             "6-Spades","6-Clubs","6-Hearts","6-Diamonds",
             "7-Spades","7-Clubs","7-Hearts","7-Diamonds",
             "8-Spades","8-Clubs","8-Hearts","8-Diamonds",
             "9-Spades","9-Clubs","9-Hearts","9-Diamonds",
             "10-Spades","10-Clubs","10-Hearts","10-Diamonds",
             "Jack-Spades","Jack-Clubs","Jack-Hearts","Jack-Diamonds",
             "Queen-Spades","Queen-Clubs","Queen-Hearts","Queen-Diamonds",
             "King-Spades","King-Clubs","King-Hearts","King-Diamonds"};
        cardsList.addAll(Arrays.asList(tempCardsList));
        
    }
    
    public String drawCard(){
        //draw Card two
        String drawCardOne = cardsList.get(random.nextInt(cardsList.size()));
        while(cardsTrash.contains(drawCardOne)){
            drawCardOne = cardsList.get(random.nextInt(cardsList.size()));
            //System.out.println("Regected repeat");
        }
        cardsTrash.add(drawCardOne);
       
        return drawCardOne;
    }
    
    public int getCardsValue() {
        return cardValue;
    }
    
    public void resetCards(){
        cardsTrash.clear();
    }
    public void computeValue(ArrayList personCards){
        cardValue = 0;
        //Finds the total number of cards player has on table
        int numCards = personCards.size();
        //System.out.println(personCards.get(0).toString().substring(0,2)); //works
       
        for(int i = 0; i<personCards.size(); i++){
            if("Ac".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 11;
            }
            else if("1-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 1;
            }
            else if("2-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 2;
            }
            else if("3-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 3;
            }
            else if("4-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 4;
            }
            else if("5-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 5;
            }
            else if("6-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 6;
            }
            else if("7-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 7;
            }
            else if("8-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 8;
            }
            else if("9-".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 9;
            }
            else if("10".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 10;
            }
            else if("Ja".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 10;
            }
            else if("Qu".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 10;
            }
            else if("Ki".equals(personCards.get(i).toString().substring(0, 2))){
                cardValue += 10;
            }
            
            
        }//end of for loop
        int numAces = 0;
        if(personCards.contains("Ace-Spades")){
            numAces +=1;
        }
        if(personCards.contains("Ace-Clubs")){
            numAces +=1;
        }
        if(personCards.contains("Ace-Hearts")){
            numAces +=1;
        }
        if(personCards.contains("Ace-Diamonds")){
            numAces +=1;
        }
            
        while((cardValue > 21) && (numAces >0)){
            int a = 0;
            while(a == 0){
                System.out.print("(Ace reduced) ");
                a+=1;
            }
            cardValue -= 10;
            numAces -= 1;
        }
        
    }// end of method
    
    
        
    
}
