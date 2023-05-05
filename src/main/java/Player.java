/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathf
 */
public class Player {
    private Money money;
    private Cards cards;


    
    private String name;
    
    public Player() {
        money = new Money();
        cards = new Cards();
    }

    public Player(String name) {
        this.name = name;
        money = new Money();
        cards = new Cards();
    }

    
    
    

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Cards getCards() {
        return cards;
    }
     
    
}
