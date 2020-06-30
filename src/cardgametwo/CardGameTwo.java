package cardgametwo;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Mantas
 */
public class CardGameTwo extends JFrame{

    public CardGameTwo(){
        initalize();
    }
    
    private void initalize(){
        add(new Board());
        setResizable(false);
        pack();
        setTitle("Card game for two!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EventQueue.invokeLater(() -> {
            CardGameTwo ex = new CardGameTwo();
            ex.setVisible(true);
        });
    }
    
}
