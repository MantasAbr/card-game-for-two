package cardgametwo;

/**
 *
 * @author Mantas
 */
public class Player {
    
    protected int chips;
    protected int chipsRaised;
    private int wins;
    private int losses;
    
    
    public Player(int chips, int wins, int losses, int chipsRaised){
        this.chips = chips;
        this.wins = wins;
        this.losses = losses;
        this.chipsRaised = chipsRaised;
    }
    
    public void setChips(int chips){
        this.chips = chips;
    }
    
    public void setLosses(int losses){
        this.losses = losses;
    }
    
    public void setWins(int wins){
        this.wins = wins;
    }
    
    public void setChipsRaised(int chipsRaised){
        this.chipsRaised = chipsRaised;
    }
    
    public int getChips(){
        return chips;
    }
    
    public int getLosses(){
        return losses;
    }
    
    public int getWins(){
        return wins;
    }
    
    public int getChipsRaised(){
        return chipsRaised;
    }
}
