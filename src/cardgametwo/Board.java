package cardgametwo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Mantas
 */
public class Board extends JPanel implements ActionListener{

    private boolean inGame;
    private Timer timer;
    private Actions actions;
    Random random = new Random();
    private List<Card> deckCards;
    private List<Card> cardsInHand;
    private List<Card> cardsInHandSecond;
    private List<Card> cardsOnTable;
    private final int BOARD_WIDTH = 840;
    private final int BOARD_HEIGHT = 600;
    private final int DELAY = 10;
    private final Color boardColor = new Color(0, 255, 33);
    private int stage = 0;
    public double allTheMoney = 0;
    public double currentHighestSum = 0;
    
    Font font = new Font("Courier new", Font.BOLD ,16);
    
    ImageIcon placeHolderIcon;
    Image placeHolder;
    Player player;
    CPU_Player playerTwo;
    
    Button finish;
    Button raise;
    Button fold;
    Button sliderBar;
    Button slider;
    public int sliderValue;
    
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        
        MouseClick clicks = new MouseClick();        
        addMouseListener(clicks);
        addMouseMotionListener(clicks);
        setFocusable(true);
        setBackground(boardColor);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        
        inGame = true;
        actions = new Actions(this);
        
        
        deckCards = actions.InitCards(deckCards);
        cardsOnTable = actions.addCardsOnTable(cardsOnTable, deckCards);
        giveCardsToPlayers();
        
        placeHolderIcon = new ImageIcon("src/images/placeholder.png");
        placeHolder = placeHolderIcon.getImage();
        player = new Player(250, 0, 0, 0);
        playerTwo = new CPU_Player(250, 0, 0, 0 ,true, cardsOnTable, cardsInHandSecond);
        sliderValue = 0;
        
        
        finish = new Button(40, 450, "src/images/button.png");        
        raise = new Button(40, 510, "src/images/button.png");
        fold = new Button(180, 450, "src/images/button.png");
        sliderBar = new Button(170, 525, "src/images/sliderbar.png");
        slider = new Button(175, 525, "src/images/slider.png");

        
        timer = new Timer(DELAY, this);
        timer.start();
        
        
        System.out.print("Cards in deck: \n");
        
        for(int i = 0 ; i < deckCards.size(); i++)
            System.out.println(deckCards.get(i).getName() + ", " + deckCards.get(i).getType());
        
        System.out.print("\nCards on table: \n");
        
        for(int i = 0 ; i < cardsOnTable.size(); i++)
            System.out.println(cardsOnTable.get(i).getName() + ", " + cardsOnTable.get(i).getType());
        
        System.out.print("\nCards on player: \n");
        
        for(int i = 0 ; i < cardsInHand.size(); i++)
            System.out.println(cardsInHand.get(i).getName() + ", " + cardsInHand.get(i).getType());
        
        System.out.print("\nCards on NPC: \n");
        
        for(int i = 0 ; i < cardsInHandSecond.size(); i++)
            System.out.println(cardsInHandSecond.get(i).getName() + ", " + cardsInHandSecond.get(i).getType());
    }        
    
    private void giveCardsToPlayers(){                

        cardsInHand = new ArrayList<>();
        cardsInHandSecond = new ArrayList<>();

        actions.giveOutCards(deckCards, cardsInHand);
        actions.giveOutCards(deckCards, cardsInHandSecond);

    }
    
    

    
    private void InGame(){
        if(!inGame){
            timer.stop();
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        drawHandCards(g2d);
        drawPlaceholder(g2d);
        drawPlayerInfo(g2d, player, 580, 460);
        drawPlayerInfo(g2d, playerTwo, 580, 40);
        drawPotInfo(g2d);
        drawButtons(g2d);
        drawSlider(g2d);
        drawCPUCards(g2d);
        if(stage == 1)
            drawFlop(g2d);
        if(stage == 2){
            drawFlop(g2d);
            drawTurn(g2d);
        }
        if(stage == 3){
            drawFlop(g2d);
            drawTurn(g2d);
            drawRiver(g2d);
        }

        
    }
    
    private void drawHandCards(Graphics g){
        
        g.drawImage(cardsInHand.get(0).getImage(), 335, 450, this);
        g.drawImage(cardsInHand.get(1).getImage(), 425, 450, this);
  
    }
    
    private void drawPlaceholder(Graphics g){
        
        g.drawImage(placeHolder, 200, 240, this);
        g.drawImage(placeHolder, 290, 240, this);
        g.drawImage(placeHolder, 380, 240, this);
        g.drawImage(placeHolder, 470, 240, this);
        g.drawImage(placeHolder, 560, 240, this);
        
    }
    
    private void drawPlayerInfo(Graphics g, Player player, int xPos, int yPos){
        
        String message1 = "Chips remaining: " + player.getChips();
        String message2 = "Wins: " + player.getWins();
        String message3 = "Losses: " + player.getLosses();        
        
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message1, xPos, yPos); //580 460
        g.drawString(message2, xPos, yPos + 20); //580 480
        g.drawString(message3, xPos, yPos + 40); //580 500
    }
    
    private void drawPotInfo(Graphics g){
        
        String message = "Money in the pot: " + Double.toString(allTheMoney);
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, 580, 200);
    }
    
    private void drawFlop(Graphics g){
        
        int xPos = 200;
        
        for(int i = 0; i < 3; i++){
            drawCard(g, cardsOnTable, xPos, 240, i);
            xPos += 90;
        }
    }
    
    private void drawTurn(Graphics g){
        drawCard(g, cardsOnTable, 470, 240, 3);
    }
    
    private void drawRiver(Graphics g){
        drawCard(g, cardsOnTable, 560, 240, 4);
    }
    
    private void drawCard(Graphics g, List<Card> wantedCardPile, int x, int y, int index){

            g.drawImage(wantedCardPile.get(index).getImage(), x, y, this);
    }
    
    private void drawButtons(Graphics g){
        
        g.drawImage(finish.getImage(), finish.getX(), finish.getY(), this);
        g.drawImage(raise.getImage(), raise.getX(), raise.getY(), this);
        g.drawImage(fold.getImage(), fold.getX(), fold.getY(), this);

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Next Turn", finish.getX() + 15, finish.getY() + 29);
        g.drawString("Fold", fold.getX() + 38, fold.getY() + 29);
        g.drawString("Raise", raise.getX() + 35, raise.getY() + 29);
    }
    
    private void drawSlider(Graphics g){
        g.drawImage(sliderBar.getImage(), sliderBar.getX(), sliderBar.getY(), this);
        g.drawImage(slider.getImage(), slider.getX(), slider.getY(), this);
        
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(getChipAmount(), 225, 560);
        
    }
    
    private void drawCPUCards(Graphics g){
        
        //cardsInHandSecond.get(0).loadImage("src/images/backside.png");
        //cardsInHandSecond.get(1).loadImage("src/images/backside.png");
        
        g.drawImage(cardsInHandSecond.get(0).getImage(), 335, 30, this);
        g.drawImage(cardsInHandSecond.get(1).getImage(), 425, 30, this);
        
    }

    
    private String getChipAmount(){
        
        int value = (int) (sliderValue * player.getChips()) / 100;
        return Integer.toString(value);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        InGame();
        repaint();
    }
    
    public class MouseClick implements MouseListener, MouseMotionListener{
               
        public boolean buttonPressed(Point mousePoint, int x1, int y1, int x2, int y2){
            if (mousePoint.getX() > x1 && mousePoint.getX() < x2 && mousePoint.getY() > y1 && mousePoint.getY() < y2) 
                return true;
            return false;
        }
      
        @Override
        public void mouseClicked(MouseEvent e) {
            
            Point mousePoint = e.getPoint();

            if (buttonPressed(mousePoint, finish.getX(), finish.getY(), finish.getX1(), finish.getY1()) &&
                actions.checkIfAllBetsAreEqual(player, playerTwo)){
                
                actions.nextAction(playerTwo);
                
            }
            
            if (buttonPressed(mousePoint, raise.getX(), raise.getY(), raise.getX1(), raise.getY1())){
                
                currentHighestSum = Integer.parseInt(getChipAmount());
                allTheMoney += Integer.parseInt(getChipAmount());
                
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point newPoint = e.getPoint();            
            
            if(slider.getX() >= (sliderBar.getX() + 5) && slider.getX1() <= (sliderBar.getX1() - 5)){
                slider.setX((int) newPoint.getX());
                slider.setX1((int) newPoint.getX() + slider.getWidth());
            
                if(slider.getX() <= (sliderBar.getX() + 5)){
                    slider.setX(sliderBar.getX() + 5);
                    slider.setX1(slider.getX() + slider.getWidth());
                }
                else if(slider.getX1() >= (sliderBar.getX1() - 5)){
                    slider.setX1(sliderBar.getX1() - 5);
                    slider.setX(slider.getX1() - slider.getWidth());
                }
                
                sliderValue = (int) slider.getX() + (slider.getWidth() / 2) - 180;
                sliderValue = (sliderValue * 84) / 100;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }   
    }   
}