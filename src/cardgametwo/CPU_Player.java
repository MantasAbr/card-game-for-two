package cardgametwo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mantas
 */
public class CPU_Player extends Player{
    
    private boolean willRaise;
    private boolean willFold;
    private boolean willSkip;
    private boolean isActive;
    
    private List<Card> cardsOnTable;
    private List<Card> cardsInHand;
    
    private float raiseFactor = 0;
    private double moneyToRaise = 0;
    
    
    public CPU_Player(int chips, int wins, int losses, int chipsRaised, 
                      boolean isActive, List<Card> cardsOnTable, List<Card> cardsInHand) {
        super(chips, wins, losses, chipsRaised);
        this.isActive = isActive;
        this.cardsOnTable = cardsOnTable;
        this.cardsInHand = cardsInHand;
    }
    
    public void determineNextMove(){
        
        willRaise = false; willFold = false; willSkip = false;            
        
        if(sameColorInHand() == true){
            willRaise = true;
            raiseFactor += 0.15;
        }
        else{
            willSkip = true;
        }
    }
    
    public int getRaiseAmount(){
        
        if(willRaise){
            moneyToRaise = chips * raiseFactor;
            
        }
        else
            moneyToRaise = 0;
        
        return (int) moneyToRaise;
    }
    
    private boolean sameColorInHand(){        
        
        System.out.println(cardsInHand.get(0).getColor());
        System.out.println(cardsInHand.get(1).getColor());
        
        if(cardsInHand.get(0).getColor().equals(cardsInHand.get(1).getColor()))
            return true;
        else
            return false;
    }
    
    public boolean getWillRaise(){
        return willRaise;
    }
    
    public boolean getWillFold(){
        return willFold;
    }
    
    public boolean getWillSkip(){
        return willSkip;
    }
    
    public boolean getIsActive(){
        return isActive;
    }
    
}
