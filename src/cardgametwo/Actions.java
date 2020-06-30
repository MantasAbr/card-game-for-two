package cardgametwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mantas
 */
public class Actions {
        
    private Random random = new Random();
    Board board;
    
    public Actions(Board board){
        this.board = board;
    }
    
    
    public List<Card> InitCards(List<Card> deckCards){
        
        deckCards = new ArrayList<>();
        
        addOneTypeOfCards("src/cardImages/clubs0.png", "Clubs", deckCards, 20);
        
        addOneTypeOfCards("src/cardImages/diamonds0.png", "Diamonds", deckCards, 23);
        
        addOneTypeOfCards("src/cardImages/hearts0.png", "Hearts", deckCards, 21);
        
        addOneTypeOfCards("src/cardImages/spades0.png", "Spades", deckCards, 21);
        
        return deckCards;
    }
    
    private void addOneTypeOfCards(String path, String type, List<Card> deckCards, int CharToChangeIndex){
                
        char[] royalty = {'J', 'Q', 'K'};        
        int royaltyCount = 0;
        String royaltyPath = path;
        
        for(int i = 0; i < 13; i++){
            if(i <= 9 && i >= 0){
                char[] spadePathChars = path.toCharArray();
                spadePathChars[CharToChangeIndex] = Integer.toString(i).charAt(0);
                path = String.valueOf(spadePathChars);
                deckCards.add(new Card(type, Integer.toString(i), path));
            }
            else if(i > 9){
                StringBuilder peoplePath = new StringBuilder(royaltyPath);
                deckCards.add(new Card(type, Integer.toString(i), peoplePath.insert(CharToChangeIndex + 1, 
                                       royalty[royaltyCount++]).toString()));
            }
        }      
    }
    
    public List<Card> addCardsOnTable(List<Card> cardsOnTable, List<Card> deckCards){
        
        cardsOnTable = new ArrayList<>();
        
        for(int i = 0; i < 5; i++){
            int pick = random.nextInt(deckCards.size() - 1);
            cardsOnTable.add(i, deckCards.get(pick));
            deckCards.remove(pick);
        }
        
        return cardsOnTable;
    }
    
    public void giveOutCards(List<Card> mainDeck, List<Card> playerDeck){
        
        Collections.shuffle(mainDeck);

        
        int firstCard = random.nextInt(mainDeck.size() - 1);        
        playerDeck.add(mainDeck.get(firstCard));
        mainDeck.remove(firstCard);
        
        int secondCard = random.nextInt(mainDeck.size() - 1);
        playerDeck.add(mainDeck.get(secondCard));
        mainDeck.remove(secondCard);
    }
    
    public void nextAction(CPU_Player playerTwo){
        
        try {
            TimeUnit.SECONDS.sleep(1);
            
            playerTwo.determineNextMove();
            
            if(playerTwo.getWillRaise()){
                board.currentHighestSum = playerTwo.getRaiseAmount();
                playerTwo.chipsRaised = playerTwo.getRaiseAmount();
                playerTwo.setChips(playerTwo.getChips() - playerTwo.getRaiseAmount());
                board.allTheMoney += playerTwo.getRaiseAmount();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkIfAllBetsAreEqual(Player player, CPU_Player playerTwo){
        
        if(player.chipsRaised == playerTwo.chipsRaised)
            return true;
        else
            return false;
    }
}
